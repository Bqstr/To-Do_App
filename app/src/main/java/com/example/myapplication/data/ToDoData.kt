package com.example.myapplication.data

import android.icu.util.ULocale
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Update
import com.example.myapplication.data.models.Priority
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName ="todo_table")
@Parcelize
data class ToDoData(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title: String,
    var priority: Priority,
    var descriptions:String
):Parcelable


