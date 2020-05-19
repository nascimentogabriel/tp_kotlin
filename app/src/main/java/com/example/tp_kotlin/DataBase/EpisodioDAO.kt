package com.example.tp_kotlin.DataBase
import androidx.room.*
import com.example.tp_kotlin.Model.Episodios

@Dao
interface EpisodioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(episodios: Episodios)
    @Update
    fun update(episodios: Episodios)
    @Delete
    fun delete(episodios: Episodios)
    @Query("SELECT * FROM Episodios")
    fun all():Array<Episodios>
    @Query("SELECT * FROM Episodios WHERE serie_id= :serie_id")
    fun allWhereSerieId(serie_id:Int):Array<Episodios>
    @Query("SELECT * FROM Episodios WHERE id= :id")
    fun EpisodioId(id:Int):Episodios
    @Query("DELETE  FROM Episodios WHERE serie_id = :serie_id")
    fun deleteSerieid(serie_id:Int)
}