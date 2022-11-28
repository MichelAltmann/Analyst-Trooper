package com.android.desafiofinalstarwars.ui.vehicles.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Starship

class StarshipsAdapter() : RecyclerView.Adapter<StarshipsAdapter.ViewHolder>() {

    private val starships : MutableList<Starship> = mutableListOf()
    lateinit var itemClickListener: (Starship) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(starship : Starship){
            val title = binding.cardViewTitle
            val subTitle = binding.cardViewSubtitle
            title.text = starship.name
            subTitle.text = "Model: " + starship.model
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
        holder.bind(starships[position])
    }

    override fun getItemCount() : Int = starships.size

    fun update(starships : List<Starship>){
        this.starships.clear()
        this.starships.addAll(starships)
        notifyDataSetChanged()
    }
}