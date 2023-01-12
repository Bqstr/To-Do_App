package com.example.myapplication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.data.ToDoDao
import com.example.myapplication.data.ToDoData
import com.example.myapplication.data.models.Priority

// as i  get understand this class just to easily access data from different source of information
class ToDoRepository(private val toDoDao: ToDoDao) {

val getAllData: LiveData<List<ToDoData>> =toDoDao.getAllData()



    suspend fun insertData(toDoData: ToDoData){
        Log.d("eownewonfowf",toDoData.priority.toString())
        toDoDao.insertData(toDoData)

    }
    suspend fun deleteData(toDoData: ToDoData){
        toDoDao.deleteData(toDoData)
        Log.d("273423748923",toDoData.title.toString())
    }
    suspend fun upadateData(toDoData: ToDoData){
        toDoDao.deleteData(toDoData)
        toDoDao.insertData(toDoData)

        //toDoDao.updateData(toDoData)
    }
    suspend fun deleteAll(){
        toDoDao.deleteAll();
    }
      fun searchData(toSearch:String):LiveData<List<ToDoData>>{
        return toDoDao.search(toSearch)
    }







        //toDoDao.update(toDoData.id, toDoData.title,toDoData.descriptions,toDoData.priority);

     //   return  toDoDao.getSomething()






}