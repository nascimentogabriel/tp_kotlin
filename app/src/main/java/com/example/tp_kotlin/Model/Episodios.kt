package com.example.tp_kotlin.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Episodios(
                @PrimaryKey(autoGenerate = true)var id :Int? = null,
                var Nome : String,
                var Nota : String,
                var serie_id:Int
)