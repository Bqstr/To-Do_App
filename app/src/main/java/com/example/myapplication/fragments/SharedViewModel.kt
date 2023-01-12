package com.example.myapplication.fragments

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.data.ToDoData
import com.example.myapplication.data.models.Priority
import com.example.myapplication.databinding.FragmentChangeBinding


//application is context
//this class just contains things that we use but dont want to show(кыскаша мусорка)
//hotya u menya i tak mnogo takih classov
class SharedViewModel(application: Application): AndroidViewModel(application) {
    var _binding: FragmentChangeBinding? = null
    val binding get() = _binding!!

    //this variable is bind(Data Binding) to the image , when it true ,its visible ...
    val emptyDatabse: MutableLiveData<Boolean> = MutableLiveData(false)
    fun checkIfDataBaseIsEmpty(toDoData: List<ToDoData>) {
        emptyDatabse.value = toDoData.isEmpty()
    }

    fun getPriority(s: String): Priority {
        return (when (s) {
            "High Priority" -> Priority.HIGH
            "Medium Priority" -> Priority.MEDIUM
            "Low Priority" -> Priority.LOW

            else -> {
                Log.d("cndjndjncnjdcn", s)
                Priority.LOW
            }
        }
                )

    }

    //just check is data is empty
    fun verifyDataFromUser(title: String, des: String): Boolean {

        return !(title.isEmpty() || des.isEmpty())

    }

    fun priorityToInt(priority: Priority): Int {
        Log.d("dvkjnjvknjksdnvjksdkvid", priority.toString())
        when (priority) {
            Priority.HIGH -> return 0;//maaa daobav animku pzh
            Priority.MEDIUM -> return 1;//maaa daobav animku pzh\
            Priority.LOW -> return 2;
            else -> return 2;
        }
    }
}







