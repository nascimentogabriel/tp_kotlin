package com.example.tp_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_kotlin.DataBase.DataBaseServer
import com.example.tp_kotlin.Model.Episodios
import com.example.tp_kotlin.Model.Serie
import kotlinx.android.synthetic.main.activity_editar_episodio.*
import kotlinx.android.synthetic.main.activity_editar_episodio.btn_atualizar
import kotlinx.android.synthetic.main.activity_exibir.*
import kotlinx.android.synthetic.main.activity_update.*

class EditarEpisodio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_episodio)

        ListTask().execute()
        btn_atualizar.setOnClickListener{
            UpdataTask().execute()
        }
        btn_deletarep.setOnClickListener {
            DeletTask().execute()
        }

    }

    inner class ListTask : AsyncTask<Unit, Unit, Episodios>() {
        override fun doInBackground(vararg params: Unit?): Episodios {
            var dataBaseServer = DataBaseServer.getInstance(applicationContext)

            var epId = intent.getIntExtra("episodioId", 0)
            var ep =dataBaseServer.episodioDAO().EpisodioId(epId)

            return ep
        }

        override fun onPostExecute(result: Episodios?) {
            edttxtnome.setText(result!!.Nome)
            ratingBar.rating = result.Nota.toFloat()
        }
    }
    inner class UpdataTask : AsyncTask<Unit, Unit, Episodios>(){

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: Unit?): Episodios? {
            var appDatabase = DataBaseServer.getInstance(applicationContext)
            var epId = intent.getIntExtra("episodioId", 0)
            var serieid = intent.getIntExtra("serieId",0)
            var novoep = Episodios(id = epId,Nome = edttxtnome.text.toString(),Nota = ratingBar.rating.toString(),serie_id =serieid )
            appDatabase.episodioDAO().update(novoep)
            return novoep
        }

        override fun onPostExecute(result: Episodios?) {
            finish()
            startActivity(Intent(applicationContext,ListaActivity::class.java))
        }

    }
    inner class DeletTask : AsyncTask<Unit, Unit, Episodios>() {
        override fun doInBackground(vararg params: Unit?): Episodios {
            var dataBaseServer = DataBaseServer.getInstance(applicationContext)
            var epId = intent.getIntExtra("episodioId", 0)
            var ep = dataBaseServer.episodioDAO().EpisodioId(epId)
            dataBaseServer.episodioDAO().delete(ep)
            return ep
        }

        override fun onPostExecute(result: Episodios?) {
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }

    }
}
