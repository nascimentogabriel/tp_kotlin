package com.example.tp_kotlin.Adaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_kotlin.Model.SerieEpisodios
import com.example.tp_kotlin.R
import kotlinx.android.synthetic.main.lista_rcyvw.view.*

class RcyvwAdpter(
    val listseries: Array<SerieEpisodios>,
    var callbackserie: (SerieEpisodios) -> Unit,
    var callbackcad: (SerieEpisodios) -> Unit

):RecyclerView.Adapter<RcyvwAdpter.SeriesViewHolder>()
{
    class SeriesViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val txtTitulo = view.txtvwtitulo
        val txtEpisodio = view.txtvwepisodio
        val botao = view.floatingActionButton

        //val txtNota = view.txtvwnota
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_rcyvw,parent,false)
        val seriesViewHolder =
            SeriesViewHolder(view)
        seriesViewHolder.itemView.setOnClickListener {
            val serieepisodio = listseries[seriesViewHolder.adapterPosition]
            callbackserie(serieepisodio)
        }

        return seriesViewHolder
    }



    override fun getItemCount(): Int = listseries.size

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val serie =listseries[position]
        //val episodio = arrayOf<Episodios>()


        holder.txtTitulo.text=serie.serie.Titulo
        holder.txtEpisodio.text=serie.episodios.size.toString()
        holder.botao.setOnClickListener {
            callbackcad(serie)
        }
        // holder.txtNota.text=serie.episodios?.Nota
    }


}