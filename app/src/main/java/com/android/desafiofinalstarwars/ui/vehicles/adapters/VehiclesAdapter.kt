package com.android.desafiofinalstarwars.ui.vehicles.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Vehicle

class VehiclesAdapter() : RecyclerView.Adapter<VehiclesAdapter.ViewHolder>() {

    private val vehicles : MutableList<Vehicle> = mutableListOf()
    lateinit var itemClickListener: (Vehicle) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(vehicle : Vehicle){
            val title = binding.cardViewTitle
            val subTitle = binding.cardViewSubtitle
            title.text = vehicle.name
            subTitle.text = "Model: " + vehicle.model
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(vehicle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vehicles[position])
    }

    override fun getItemCount() : Int = vehicles.size

    fun update(vehicles : List<Vehicle>){
        this.vehicles.clear()
        this.vehicles.addAll(vehicles)
        notifyDataSetChanged()
    }
}