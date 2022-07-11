package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

class NewsListAdapter(
    private var articles: MutableList<Article>?,
    private val context: Context? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class NewsItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        //include image, title, description, and date for article
        val newsImage : ImageView = itemView.findViewById(R.id.newsImage)
        val newsTitle : TextView = itemView.findViewById(R.id.newsTitle)
        val newsDescription : TextView = itemView.findViewById(R.id.newsDescription)
        val newsDate : TextView = itemView.findViewById(R.id.newsDate)
        init {
            //TODO - need to show article clicked, right now it is hardcoded to just show first one
            itemView.setOnClickListener {
                // should open default browser to show the news details
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articles!![1].url))
                context?.startActivity(browserIntent)
            }
        }
    }

    //obtain new headline items
    internal fun refreshNewsItems (newarticles: MutableList<com.kwabenaberko.newsapilib.models.Article>){
        val initialSize = this.articles?.size ?: 0
        if (this.articles == null) {
            this.articles = mutableListOf()
        }
        this.articles?.clear()
        for ((i) in newarticles.withIndex()) {
            this.articles!!.add(Article())
            this.articles!![i].title = newarticles[i].title
            this.articles!![i].description = newarticles[i].description
            this.articles!![i].url = newarticles[i].url
            this.articles!![i].urlToImage = newarticles[i].urlToImage
            this.articles!![i].publishedAt = newarticles[i].publishedAt
        }

        notifyItemRangeChanged(1, newarticles.size ?: 0)
        if (newarticles.size < initialSize) {
            val sizeDifference = initialSize - newarticles.size
            notifyItemRangeRemoved(newarticles.size, sizeDifference)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return 0
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = parent.inflate(R.layout.news_list_item)
        return NewsItemViewHolder(v)
    }

    //only return the top 4 results
    override fun getItemCount(): Int {
        return 4
    }

    //display headline items
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= 1) {
            if (articles != null) {
                val idx =  position - 1
                if (articles?.indices?.contains(idx) == true) {
                    val a = articles!![idx]
                    val newsItemViewHolder = (holder as NewsItemViewHolder)
                    newsItemViewHolder.newsTitle.text  = a.title
                    newsItemViewHolder.newsDescription.text = a.description
                    newsItemViewHolder.newsDate.text = a.publishedAt

                    if (context != null) {
                        Glide.with(context).load(a.urlToImage)
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground)
                            .fallback(R.drawable.ic_launcher_foreground)
                            .into(newsItemViewHolder.newsImage)
                    }
                }
            }
        }
    }
}