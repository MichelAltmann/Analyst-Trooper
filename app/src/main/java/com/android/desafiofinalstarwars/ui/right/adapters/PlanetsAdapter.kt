package com.android.desafiofinalstarwars.ui.right.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Planet

class PlanetsAdapter() : RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {

    private val planets : MutableList<Planet> = mutableListOf()
    lateinit var itemClickListener: (Planet) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(planet: Planet){
            val title = binding.cardViewTitle
            val subTitle = binding.cardViewSubtitle
            val subTitle2 = binding.cardViewSubtitle2
            title.text = planet.name
            subTitle.text = "Water per km: " + planet.surfaceWater
            subTitle2.text = "Population: " + planet.population
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
        holder.bind(planets[position])
    }

    override fun getItemCount() : Int = planets.size

    fun update(planets: List<Planet>){
        this.planets.clear()
        this.planets.addAll(planets)
        notifyDataSetChanged()
    }
}