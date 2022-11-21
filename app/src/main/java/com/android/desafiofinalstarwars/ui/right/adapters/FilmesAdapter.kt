package com.android.desafiofinalstarwars.ui.right.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Movie

class FilmesAdapter() : RecyclerView.Adapter<FilmesAdapter.ViewHolder>() {

    private val movies : MutableList<Movie> = mutableListOf()
    lateinit var itemClickListener: (Movie) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(movie: Movie){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            val subTitulo2 = binding.cardViewSubtitulo2
            titulo.text = movie.title
            subTitulo.text = "Director: " + movie.director
            subTitulo2.text = "Release Date: " + movie.releaseDate
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(movies[position])
    }

    override fun getItemCount() : Int = movies.size

    fun atualiza(movies: List<Movie>){
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}