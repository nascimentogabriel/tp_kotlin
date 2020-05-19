package com.example.tp_kotlin.Model

import androidx.room.Embedded
import androidx.room.Relation

class SerieEpisodios(
    @Embedded var serie: Serie,
    @Relation(
        parentColumn = "id",
        entityColumn = "serie_id"
    )
 var episodios : List<Episodios>
){
//    override fun toString(): String {
//        return "${serie.Titulo}:${episodios?.Nome}:${episodios?.Nota}"
//    }
}
