package com.example.myapplication.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.ToDoData
import com.example.myapplication.data.models.Priority

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var arr = emptyList<ToDoData>();
companion object{
    fun getArr(){

    }
}




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(itemView)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)











    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.itemView.findViewById<TextView>(R.id.title_txt).text =arr[position].title
        holder.itemView.findViewById<TextView>(R.id.des_txt).text =arr[position].descriptions
       // holder.itemView.findViewById<CardView>(R.id.row_back).setOnClickListener{
       var element =holder.itemView.findViewById<ConstraintLayout>(R.id.row_back)
        element.setOnClickListener{
            Log.d("vnjdfnvjknvnvjk","kfkdsvkdnvjknvjknvjk")
            var action= ListFragmentDirections.actionListFragmentToChangeFragment(arr[position]);
            holder.itemView.findNavController().navigate(action)

        }


        when(arr[position].priority){
            Priority.HIGH-> {
                holder.itemView.findViewById<CardView>(R.id.priority_txt).setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context, R.color.red
                    )
                )
                Log.d("jabd","red")

            }
         Priority.MEDIUM -> {
             holder.itemView.findViewById<CardView>(R.id.priority_txt)
                 .setCardBackgroundColor(
                     ContextCompat.getColor(
                         holder.itemView.context, R.color.yellow
                     )
                 )
             Log.d("jabd","yellow")

         }
           Priority.LOW-> {
               holder.itemView.findViewById<CardView>(R.id.priority_txt)
                   .setCardBackgroundColor(
                       ContextCompat.getColor(
                           holder.itemView.context, R.color.gren
                                )
                            )
               Log.d("jabd","gren")

           }

                }




                }




    override fun getItemCount(): Int {
        return arr.size
    }
    public fun setData(obj:List<ToDoData>){
        this.arr =obj;
        refreshing()
    }
    fun refreshing(){
        notifyDataSetChanged()
    }



}