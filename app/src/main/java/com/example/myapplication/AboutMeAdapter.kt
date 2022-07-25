package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_expander_information.view.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

//https://www.codevscolor.com/android-kotlin-delete-item-recyclerview

class AboutMeAdapter(val informationList: List<Information>) : RecyclerView.Adapter<AboutMeAdapter.InformationViewHolder>(){

    class InformationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
//    private var listData: MutableList<Information> = informationList as MutableList<Information>
//    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
//
//        fun bind(information: Information, index: Int){
//            val title = view.findViewById<TextView>(R.id.tv_title)
////            val information1 = view.findViewById<TextView>(R.id.information_list)
//            val button = view.findViewById<Button>(R.id.et_remove_med)
//
//            title.text = title.toString()
////            information1.text = information1.toString()
//
////            Glide.with(view.context).load(property.image).centerCrop().into(imageView)
//
//            button.setOnClickListener{deleteItem(index)}
//
//        }
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        return InformationViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.simple_expander_information, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        val currInformation: Information = informationList[position]

//        holder.bind(listData[position], position)
        
        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {

            tv_title.text = currInformation.name
            val textFields = currInformation.aboutMeAdapter()
            val arrayAdapter = ArrayAdapter(context, R.layout.simple_expander_text, textFields.toTypedArray())
            information_list.adapter = arrayAdapter



            val isExpandable : Boolean = currInformation.expandable
            rl_expandable_layout.visibility = if (isExpandable) View.GONE else View.VISIBLE

            linear_layout.setOnClickListener {
                // toggle the box being expandable
                currInformation.expandable = !currInformation.expandable
                notifyItemChanged(position)
            }

            button.setOnClickListener {
                // toggle the box being expandable
                if(currInformation is Appointments){
                    val fis1 = FileInputStream(context?.filesDir.toString() + "appointments_list.meditrack")
                    val ois1 = ObjectInputStream(fis1)
                    var appointmentsList: Array<Appointments> =
                        ois1.readObject() as Array<Appointments>

                    ois1.close()
                    fis1.close()

                    for(i in appointmentsList.indices){
                        if(appointmentsList[i].name == currInformation.name){
                            val tempappointmentsList = appointmentsList.toMutableList()
                            tempappointmentsList.removeAt(i)
                            appointmentsList = tempappointmentsList.toTypedArray()
                            break
                        }
                    }

                    val apptfos =
                        FileOutputStream(context?.filesDir.toString() + "appointments_list.meditrack")
                    val apptoos = ObjectOutputStream(apptfos)
                    apptoos.writeObject(appointmentsList)
                    apptoos.close()
                }
                else if(currInformation is Conditions){
                    val fis1 = FileInputStream(context?.filesDir.toString() + "conditions_list.meditrack")
                    val ois1 = ObjectInputStream(fis1)
                    var conditionsList: Array<Conditions> =
                        ois1.readObject() as Array<Conditions>

                    ois1.close()
                    fis1.close()

                    for(i in conditionsList.indices){
                        if(conditionsList[i].name == currInformation.name){
                            val tempappointmentsList = conditionsList.toMutableList()
                            tempappointmentsList.removeAt(i)
                            conditionsList = tempappointmentsList.toTypedArray()
                            break
                        }
                    }

                    val condfos =
                        FileOutputStream(context?.filesDir.toString() + "conditions_list.meditrack")
                    val condoos = ObjectOutputStream(condfos)
                    condoos.writeObject(conditionsList)
                    condoos.close()
                }
                else if(currInformation is AboutMeMeds){
                    val fis1 = FileInputStream(context?.filesDir.toString() + "medications_list.meditrack")
                    val ois1 = ObjectInputStream(fis1)
                    var medicationsList: Array<Array<Meds>> =
                        ois1.readObject() as Array<Array<Meds>>

                    ois1.close()
                    fis1.close()

                    for(i in medicationsList.indices){
                        for(j in medicationsList[i].indices){
                            if(medicationsList[i][j].name == currInformation.name){
                                var newList = medicationsList[i].toMutableList()
                                newList.removeAt(j)
                                medicationsList[i] = newList.toTypedArray()
                                break
                            }
                        }
                    }

                    val condfos =
                        FileOutputStream(context?.filesDir.toString() + "medications_list.meditrack")
                    val condoos = ObjectOutputStream(condfos)
                    condoos.writeObject(medicationsList)
                    condoos.close()
                }
                else if(currInformation is IncidentAboutMe){
                    val fis = FileInputStream(context?.filesDir.toString() + "incident_list.meditrack")
                    val ois = ObjectInputStream(fis)

                    var incidentsList: Array<Incident> =
                        ois.readObject() as Array<Incident>

                    ois.close()
                    fis.close()


                    for(i in incidentsList.indices){
                        if(incidentsList[i].symptom == currInformation.name && incidentsList[i].date == currInformation.date){
                            val tempIncidentsList = incidentsList.toMutableList()
                            tempIncidentsList.removeAt(i)
                            incidentsList = tempIncidentsList.toTypedArray()
                            break
                        }
                    }
                    val incidentfos =
                        FileOutputStream(context?.filesDir.toString() + "incident_list.meditrack")
                    val incidentsoos = ObjectOutputStream(incidentfos)
                    incidentsoos.writeObject(incidentsList)
                    incidentsoos.close()
                }

                linear_layout.visibility = View.GONE
            }


            val params = information_list.layoutParams
            params.height =  200 * textFields.size
            information_list.layoutParams = params
            information_list.requestLayout()

        }


    }

//    fun deleteItem(index: Int){
//        listData.removeAt(index)
//        notifyDataSetChanged()
//    }
//
//
    override fun getItemCount(): Int {
        return informationList.size
    }
//
//    fun setItems(items: List<Information>){
////        listData = items
////        notifyDataSetChanged()
//    }
}

