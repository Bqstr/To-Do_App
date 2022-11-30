package com.example.myapplication.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.ToDo
import com.example.myapplication.data.models.Priority

//DAO -data access object
@Dao
interface ToDoDao {
    @Query("SELECT  * FROM todo_table ORDER BY ID ASC")
    public  fun getAllData(): LiveData<List<ToDoData>>



    @Insert(onConflict =OnConflictStrategy.IGNORE)//<--------------------|
    //when we try to insert the same object "OnConflict will ignore it---|
    suspend fun insertData(toDoData: ToDoData) //suspend -fun will work in coroutines( on background)

    @Delete
    suspend fun deleteData(toDoData: ToDoData)

    @Query("Delete from todo_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateData(toDoData: ToDoData){
        Log.d("dsbshdbjdsc","in dao ${toDoData.id.toString()}")
    }
    @Query("Update todo_table set title ='bilyaaa'")
    suspend fun update(id:Int ,name:String,des:String,priority: Priority){

    }

}