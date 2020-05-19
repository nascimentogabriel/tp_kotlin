package com.example.tp_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tp_kotlin.DataBase.DataBaseServer
import com.example.tp_kotlin.Model.Episodios
import com.example.tp_kotlin.Model.Serie
import kotlinx.android.synthetic.main.activity_cad_serie.*
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        var serieId =intent.getIntExtra("serieId",0)
            ListaTask().execute()
       btn_atualizar.setOnClickListener {
           UpdataTask().execute()
       }
    }

    inner class UpdataTask : AsyncTask<Unit, Unit, Serie>(){

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: Unit?): Serie? {
            var appDatabase = DataBaseServer.getInstance(applicationContext)
            var serieId =intent.getIntExtra("Id", 0)
            var serieatt = Serie(id=serieId,Titulo = edttxteditar.text.toString(),Ano = "1880")
            appDatabase.serieDao().update(serieatt)
            return serieatt
        }

        override fun onPostExecute(result: Serie?) {
            finish()
            startActivity(Intent(applicationContext,ListaActivity::class.java))
        }

    }
    inner class ListaTask : AsyncTask<Unit, Unit, Serie>() {

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: Unit?): Serie? {
            var appDatabase = DataBaseServer.getInstance(applicationContext)
            var lista = appDatabase.serieDao().all()

            var serieatt = Serie(Titulo = edttxteditar.text.toString(), Ano = "1999")

            return serieatt!!
        }

        override fun onPostExecute(result: Serie?) {
            var serietitulo =intent.getStringExtra("titulo")
            edttxteditar.setText(serietitulo)
            super.onPostExecute(result)
        }



    }
    }
