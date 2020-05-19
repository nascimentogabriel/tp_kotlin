package com.example.tp_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AndroidException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_kotlin.DataBase.DataBaseServer
import com.example.tp_kotlin.Model.Episodios
import com.example.tp_kotlin.Model.Serie
import com.example.tp_kotlin.Model.SerieEpisodios
import kotlinx.android.synthetic.main.activity_exibir.*
import kotlinx.android.synthetic.main.activity_lista.*
import kotlinx.android.synthetic.main.exibir_rcyvw.view.*
import kotlinx.android.synthetic.main.lista_rcyvw.view.*
import kotlin.coroutines.coroutineContext

class ExibirActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibir)
        btn_edtr.setOnClickListener {
            var id  = intent.getIntExtra("serieId",0)
            var titulo = intent.getStringExtra("TituloId")
            var intent = Intent(applicationContext,UpdateActivity::class.java)
            intent.putExtra("Id",id)
            intent.putExtra("titulo",titulo)
            finish()
            startActivity(intent)
        }

        btn_deletar.setOnClickListener {
            DeletTask().execute()
        }

        ListTask().execute()
        var tituloserie = intent.getStringExtra("TituloId")
        txtvwtituloid.text = tituloserie

    }


    class Exibirrcyvw
        (
        val listepisodios: Array<Episodios>,
        var callbackep: (Episodios) -> Unit
    ) : RecyclerView.Adapter<Exibirrcyvw.SeriesViewHolder>() {
        class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val txtNomeep = view.txtvwnomeep
            val txtNotaep = view.txtvwnotaep
            val botao = view.floatingActionButton
            //val txtNota = view.txtvwnota
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.exibir_rcyvw, parent, false)
            val seriesViewHolder = SeriesViewHolder(view)
            seriesViewHolder.itemView.setOnClickListener {
                val episodio = listepisodios[seriesViewHolder.adapterPosition]
                callbackep(episodio)
            }
            return seriesViewHolder
        }

        override fun getItemCount(): Int = listepisodios.size

        override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
            val serie = listepisodios[position]
            //val episodio = arrayOf<Episodios>()


            holder.txtNomeep.text = serie.Nome
            holder.txtNotaep.rating = serie.Nota.toFloat()
            // holder.txtNota.text=serie.episodios?.Nota
        }
    }

    inner class ListTask : AsyncTask<Unit, Unit, Array<Episodios>>() {
        override fun doInBackground(vararg params: Unit?): Array<Episodios> {
            var dataBaseServer = DataBaseServer.getInstance(applicationContext)

            var serieId = intent.getIntExtra("serieId", 0)
            var series = dataBaseServer.episodioDAO().allWhereSerieId(serieId)
            return series
        }

        override fun onPostExecute(result: Array<Episodios>?) {
            var serieId = intent.getIntExtra("serieId", 0)
            var rcyvwAdpter = Exibirrcyvw(result!!, this::callbackep)
            rcyvw_exibir.adapter = rcyvwAdpter
            rcyvw_exibir.layoutManager = LinearLayoutManager(applicationContext)
        }

        fun callbackep(episodios: Episodios) {
            var intent = Intent(applicationContext, EditarEpisodio::class.java)
            intent.putExtra("serieId",episodios.serie_id)
            intent.putExtra("episodioId", episodios.id)
            finish()
            startActivity(intent)

        }
    }

        inner class DeletTask : AsyncTask<Unit, Unit, Array<Serie>>() {
            override fun doInBackground(vararg params: Unit?): Array<Serie> {
                var dataBaseServer = DataBaseServer.getInstance(applicationContext)
                var id = intent.getIntExtra("serieId",0)

                var serie = dataBaseServer.serieDao().unicaEntidade(id)

                dataBaseServer.serieDao().delete(serie)
                dataBaseServer.episodioDAO().deleteSerieid(id)

                var series = dataBaseServer.episodioDAO().allWhereSerieId(id)

                return dataBaseServer.serieDao().all()
            }

            override fun onPostExecute(result: Array<Serie>?) {
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }

        }
}

