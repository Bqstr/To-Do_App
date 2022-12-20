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
import com.example.myapplication.ToDo
import com.example.myapplication.data.ToDoData
import com.example.myapplication.data.models.Priority
import com.example.myapplication.databinding.RowLayoutBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var arr = emptyList<ToDoData>();
companion object{
    fun getArr(){

    }
}





    class MyViewHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(toDoData:ToDoData){
            binding.toDoData =toDoData
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent:ViewGroup):MyViewHolder{
                val layoutBinding =LayoutInflater.from(parent.context)
                val binding =RowLayoutBinding.inflate(layoutBinding ,parent,false)
                return MyViewHolder(binding)
            }
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_layout, parent, false)
        return MyViewHolder.from(parent)
    }












    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =arr[position]
        holder.bind(currentItem)








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