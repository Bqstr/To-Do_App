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
    @Query("Select * FROM todo_table where title like :toSearch")
     fun search(toSearch:String): LiveData<List<ToDoData>>

    @Query("select * from todo_table order by case when priority like 'H%' then 1 when priority like 'M%' then 2 when priority like 'L%' then 3 END;")
    fun sortToHighest(): LiveData<List<ToDoData>>

    @Query("select * from todo_table order by case when priority like 'L%' then 1 when priority like 'M%' then 2 when priority like 'H%' then 3 END;")
    fun sortToLowest(): LiveData<List<ToDoData>>

}