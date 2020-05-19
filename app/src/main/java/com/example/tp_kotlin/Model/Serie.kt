package com.example.tp_kotlin.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Serie(
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    var Titulo :String,
    var Ano:String
) {
}