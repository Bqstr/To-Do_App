package com.example.myapplication.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ToDo
import com.example.myapplication.data.models.Priority
import com.example.myapplication.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
//application
class ToDoViewModel(application:Application): AndroidViewModel(application) {
private val repository : ToDoRepository
    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
     val getAllData :LiveData<List<ToDoData>>
    init {

    repository= ToDoRepository(toDoDao)
    getAllData =repository.getAllData


    }
    fun insertData(toDoData: ToDoData){
        //wooooo its coroutines
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(toDoData)
        }
    }
    fun deleteData(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteData(toDoData)
        }
    }
    fun updateData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.upadateData(toDoData)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteAll();
        }
    }
     fun searchData(toSearch:String) : LiveData<List<ToDoData>>{
        return repository.searchData(toSearch)
    }






}