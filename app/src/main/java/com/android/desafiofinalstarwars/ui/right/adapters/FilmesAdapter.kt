package com.android.desafiofinalstarwars.ui.right.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Filme
import com.android.desafiofinalstarwars.model.Planeta

class FilmesAdapter() : RecyclerView.Adapter<FilmesAdapter.ViewHolder>() {

    private val filmes : MutableList<Filme> = mutableListOf()
    lateinit var itemClickListener: (Filme) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(filme: Filme){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            val subTitulo2 = binding.cardViewSubtitulo2
            titulo.text = filme.titulo
            subTitulo.text = "Director: " + filme.diretor
            subTitulo2.text = "Release Date: " + filme.dataLancamento
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(filme)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(filmes[position])
    }

    override fun getItemCount() : Int = filmes.size

    fun atualiza(filmes: List<Filme>){
        this.filmes.clear()
        this.filmes.addAll(filmes)
        notifyDataSetChanged()
    }
}