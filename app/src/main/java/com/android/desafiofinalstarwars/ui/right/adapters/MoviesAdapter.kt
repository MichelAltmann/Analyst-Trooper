package com.android.desafiofinalstarwars.ui.right.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Movie

class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val movies : MutableList<Movie> = mutableListOf()
    lateinit var itemClickListener: (Movie) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            val title = binding.cardViewTitle
            val subTitle = binding.cardViewSubtitle
            val subTitle2 = binding.cardViewSubtitle2
            title.text = movie.title
            subTitle.text = "Director: " + movie.director
            subTitle2.text = "Release Date: " + movie.releaseDate
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
        holder.bind(movies[position])
    }

    override fun getItemCount() : Int = movies.size

    fun update(movies: List<Movie>){
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}