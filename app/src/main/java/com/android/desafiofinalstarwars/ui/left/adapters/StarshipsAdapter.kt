package com.android.desafiofinalstarwars.ui.left.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Starship

class StarshipsAdapter() : RecyclerView.Adapter<StarshipsAdapter.ViewHolder>() {

    private val starships : MutableList<Starship> = mutableListOf()
    lateinit var itemClickListener: (Starship) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(starship : Starship){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            titulo.text = starship.name
            subTitulo.text = "Model: " + starship.model
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(starship)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(starships[position])
    }

    override fun getItemCount() : Int = starships.size

    fun atualiza(starships : List<Starship>){
        this.starships.clear()
        this.starships.addAll(starships)
        notifyDataSetChanged()
    }
}