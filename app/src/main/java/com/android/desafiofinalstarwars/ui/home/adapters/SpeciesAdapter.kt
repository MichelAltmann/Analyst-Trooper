package com.android.desafiofinalstarwars.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Specie

class SpeciesAdapter() : RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {

    private val especies : MutableList<Specie> = mutableListOf()
    lateinit var itemClickListener: (Specie) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(specie: Specie){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            val subTitulo2 = binding.cardViewSubtitulo2
            titulo.text = specie.nome
            subTitulo.text = "Average Height: " + specie.alturaMedia
            subTitulo2.text = "Lifespan: " + specie.mediaDeVida
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(specie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(especies[position])
    }

    override fun getItemCount() : Int = especies.size

    fun atualiza(especies: List<Specie>){
        this.especies.clear()
        this.especies.addAll(especies)
        notifyDataSetChanged()
    }
}