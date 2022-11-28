package com.android.desafiofinalstarwars.ui.characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Specie

class SpeciesAdapter() : RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {

    private val species : MutableList<Specie> = mutableListOf()
    lateinit var itemClickListener: (Specie) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(specie: Specie){
            val title = binding.cardViewTitle
            val subTitle = binding.cardViewSubtitle
            val subTitle2 = binding.cardViewSubtitle2
            title.text = specie.name
            subTitle.text = "Average Height: " + specie.averageHeight
            subTitle2.text = "Lifespan: " + specie.averageLifespan
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
        holder.bind(species[position])
    }

    override fun getItemCount() : Int = species.size

    fun update(especies: List<Specie>){
        this.species.clear()
        this.species.addAll(especies)
        notifyDataSetChanged()
    }
}