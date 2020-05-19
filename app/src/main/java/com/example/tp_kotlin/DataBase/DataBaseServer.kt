package com.example.tp_kotlin.DataBase

import android.content.Context
import androidx.room.Room

class DataBaseServer
{
    companion object{
        var instance : AppDatabase? = null
        val nomeDB = "Filmesdb.sql"
        fun getInstance(context: Context):AppDatabase{
            if(instance==null){
                instance = Room.databaseBuilder(context,AppDatabase::class.java,nomeDB
                )

                    .build()
            }
        return instance as AppDatabase
        }

    }
}