package com.example.myapplication.fragments

import android.graphics.Color.red
import android.os.Build
import android.view.View
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.ToDoData
import com.example.myapplication.data.ToDoDatabase
import com.example.myapplication.data.models.Priority
import com.example.myapplication.databinding.FragmentChangeBinding
import com.example.myapplication.fragments.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapter {
    lateinit var binding : FragmentChangeBinding

    //this class is for listeners that we just write by using dataBinding
    companion object{
        @androidx.databinding.BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view:FloatingActionButton,navigate:Boolean){
            view.setOnClickListener{
                if(navigate){

                view.findNavController().navigate(R.id.action_listFragment_to_createFragment)}
            }

        }

        @androidx.databinding.BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
            when(emptyDatabase.value){
                true -> view.visibility =View.VISIBLE
                false-> view.visibility =View.GONE
            }
        }
        @androidx.databinding.BindingAdapter("android:changeSelectedItem")
        @JvmStatic
        fun parsePrioritytoInt(view: Spinner, priority:Priority){
            when(priority){
                Priority.HIGH -> view.setSelection(0);//maaa daobav animku pzh
                Priority.MEDIUM -> view.setSelection(1);//maaa daobav animku pzh\
                Priority.LOW -> view.setSelection(2);
                else -> view.setSelection(2);
            }
        }
        @RequiresApi(Build.VERSION_CODES.M)
        @androidx.databinding.BindingAdapter("android:parsePriorityToColor")
        @JvmStatic
        fun parsePriorityToColor(card:CardView,priority: Priority){
            when(priority){
                Priority.HIGH ->{ card.setCardBackgroundColor(card.context.getColor(R.color.red)) }
                Priority.MEDIUM ->{ card.setCardBackgroundColor(card.context.getColor(R.color.yellow)) }
                Priority.LOW ->{ card.setCardBackgroundColor(card.context.getColor(R.color.gren)) }

            }
        }

        @androidx.databinding.BindingAdapter("android:sendDataToUpdateList")
        @JvmStatic
        fun sendDataToUpdateList(view :ConstraintLayout,toDoData:ToDoData){
            view.setOnClickListener{
                val action =ListFragmentDirections.actionListFragmentToChangeFragment(toDoData)
                view.findNavController().navigate(action)
            }
        }


    }
}