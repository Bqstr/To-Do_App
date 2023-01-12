package com.example.myapplication.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.ToDoData
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
            fun from(parent:ViewGroup): MyViewHolder {
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
        val toDoDiffUtil =ToDoDiffUtil(arr ,obj)
        val toDoListResult =DiffUtil.calculateDiff(toDoDiffUtil)
        this.arr = obj;
        toDoListResult.dispatchUpdatesTo(this)

        //i used diffUtill bc it's faster
        //refreshing()
    }
    fun refreshing(){
        //Just fact: this update all items ,so that  it takes a lot of resources
        notifyDataSetChanged()

    }



}