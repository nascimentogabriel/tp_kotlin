package com.example.tp_kotlin

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_kotlin.Adaper.RcyvwAdpter
import com.example.tp_kotlin.DataBase.DataBaseServer
import com.example.tp_kotlin.Model.SerieEpisodios
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        ListTask().execute()

    }
    inner class ListTask : AsyncTask<Unit,Unit,Array<SerieEpisodios>>(){
        override fun doInBackground(vararg params: Unit?): Array<SerieEpisodios> {
            var dataBaseServer = DataBaseServer.getInstance(applicationContext)

            //dataBaseServer.serieDao().insert(Serie(Titulo = "novoteste",Ano = "2000"))


            var series = dataBaseServer.serieDao().allWhithSerie()

            return series
        }

        override fun onPostExecute(result: Array<SerieEpisodios>?) {

            var rcyvwAdpter = RcyvwAdpter(
                result!!,
                this::callbackserie,
                this::callbackcadastro
            )
            rcyvw.adapter = rcyvwAdpter
            rcyvw.layoutManager = LinearLayoutManager(applicationContext)

        }
        fun callbackserie(serieEpisodios: SerieEpisodios){
            var intent = Intent(this@ListaActivity,ExibirActivity::class.java)
            intent.putExtra("TituloId",serieEpisodios.serie.Titulo)
            intent.putExtra("serieId",serieEpisodios.serie.id)

            startActivity(intent)

        }
        fun callbackcadastro(serieEpisodios: SerieEpisodios) {


                var intent = Intent(applicationContext, CadEpisodioActivity::class.java)
                    intent.putExtra("serieId",serieEpisodios.serie.id)
            finish()
                startActivity(intent)
        }



    }


}
