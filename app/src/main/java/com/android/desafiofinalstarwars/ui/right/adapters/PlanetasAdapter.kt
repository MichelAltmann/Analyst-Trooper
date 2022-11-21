package com.android.desafiofinalstarwars.ui.right.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Planet

class PlanetasAdapter() : RecyclerView.Adapter<PlanetasAdapter.ViewHolder>() {

    private val planets : MutableList<Planet> = mutableListOf()
    lateinit var itemClickListener: (Planet) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(planet: Planet){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            val subTitulo2 = binding.cardViewSubtitulo2
            titulo.text = planet.name
            subTitulo.text = "Water per km: " + planet.surfaceWater
            subTitulo2.text = "Population: " + planet.population
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(planet)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(planets[position])
    }

    override fun getItemCount() : Int = planets.size

    fun atualiza(planets: List<Planet>){
        this.planets.clear()
        this.planets.addAll(planets)
        notifyDataSetChanged()
    }
}