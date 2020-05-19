package com.example.tp_kotlin

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.DatabaseConfiguration
import androidx.room.Room
import com.example.tp_kotlin.DataBase.AppDatabase
import com.example.tp_kotlin.DataBase.DataBaseServer
import com.example.tp_kotlin.Model.Serie
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       setContentView(R.layout.activity_main)
    btn_lista.setOnClickListener {
        startActivity(Intent(this,ListaActivity::class.java))
    }
    btn_cadastrar.setOnClickListener {
        startActivity(Intent(this,CadSerieActivity::class.java))
    }

}
}
