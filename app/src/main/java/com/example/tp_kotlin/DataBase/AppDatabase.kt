package com.example.tp_kotlin.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp_kotlin.Model.Episodios

import com.example.tp_kotlin.Model.Serie

@Database(
    entities = arrayOf(
        Serie::class,Episodios::class
    ),version = 1
)
abstract class AppDatabase :RoomDatabase() {

    abstract fun serieDao() : SerieDAO
    abstract fun episodioDAO() : EpisodioDAO
}