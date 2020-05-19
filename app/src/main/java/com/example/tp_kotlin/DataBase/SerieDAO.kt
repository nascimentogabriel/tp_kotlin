package com.example.tp_kotlin.DataBase

import androidx.room.*
import com.example.tp_kotlin.Model.Serie
import com.example.tp_kotlin.Model.SerieEpisodios

@Dao
interface SerieDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(serie: Serie)
    @Update
    fun update(serie:Serie)
    @Delete
    fun delete(serie: Serie)
    @Query("SELECT * FROM Serie")
    fun all():Array<Serie>
    @Query("SELECT * FROM Serie WHERE id = :id")
    fun unicaEntidade(id:Int):Serie
    @Transaction
    @Query("SELECT * FROM Serie")
    fun allWhithSerie(): Array<SerieEpisodios>

}

