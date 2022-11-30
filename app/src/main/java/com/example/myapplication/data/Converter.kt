package com.example.myapplication.data

import androidx.room.TypeConverter
import com.example.myapplication.data.models.Priority

//class to convert Priorities from our custom var to String
class Converter {
    @TypeConverter
    fun fromPriority(priotity: Priority) : String {

        return priotity.name
    }
    @TypeConverter
    fun toProitority(priority:String): Priority {

        return Priority.valueOf(priority)
    }
}