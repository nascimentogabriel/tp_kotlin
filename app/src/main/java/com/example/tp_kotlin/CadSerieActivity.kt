package com.example.tp_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.tp_kotlin.DataBase.AppDatabase
import com.example.tp_kotlin.DataBase.DataBaseServer
import com.example.tp_kotlin.Model.Episodios
import com.example.tp_kotlin.Model.Serie
import com.example.tp_kotlin.Model.SerieEpisodios
import kotlinx.android.synthetic.main.activity_cad_serie.*


class CadSerieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cad_serie)

        var titulo = edttxttitulo.text
        btn_cadastrar.setOnClickListener {
            if (titulo.isNullOrEmpty()){
                Toast.makeText(this,"Preencha Todos os campos"
                    ,Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                InsertTask().execute()



            }
        }
    }
         inner class InsertTask : AsyncTask<Unit,Unit,Serie>(){
            @SuppressLint("WrongThread")
            override fun doInBackground(vararg params: Unit?): Serie? {
                var appDatabase = DataBaseServer.getInstance(applicationContext)
                var novaserie = Serie(Titulo = edttxttitulo.text.toString(),Ano = "2000")
                appDatabase.serieDao().insert(novaserie)
                //appDatabase.episodioDAO().insert(Episodios(Nome = "ep5",Nota = "1",serie_id = 9))
                //appDatabase.episodioDAO().insert(Episodios(Nome = "ep1",Nota = "5",serie_id = 1))
                return novaserie!!
            }
             override fun onPostExecute(result: Serie?) {
                // Toast.makeText(applicationContext,"salvo",Toast.LENGTH_SHORT).show()
                 finish()
                 startActivity(Intent(applicationContext,ListaActivity::class.java))
             }
         }
}
