package com.example.tp_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_kotlin.DataBase.DataBaseServer
import com.example.tp_kotlin.Model.Episodios
import com.example.tp_kotlin.Model.Serie
import com.example.tp_kotlin.Model.SerieEpisodios
import kotlinx.android.synthetic.main.activity_cad_episodio.*
import kotlinx.android.synthetic.main.activity_cad_serie.*
import kotlinx.android.synthetic.main.activity_lista.*

class CadEpisodioActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cad_episodio)
        btn_cadastrarep.setOnClickListener {
            InsertTask().execute()
        }
    }


    inner class InsertTask : AsyncTask<Unit, Unit, Episodios>(){
        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: Unit?): Episodios? {
            var appDatabase = DataBaseServer.getInstance(applicationContext)
        var serieid = intent.getIntExtra("serieId",0)
            var novoepisodio = Episodios(Nome = edttxnomeep.text.toString(),Nota =notaep.rating.toString(),serie_id = serieid)
            appDatabase.episodioDAO().insert(novoepisodio)

            return novoepisodio!!
        }
        override fun onPostExecute(result: Episodios?) {
            // Toast.makeText(applicationContext,"salvo",Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(applicationContext,ListaActivity::class.java))
        }
    }

}
