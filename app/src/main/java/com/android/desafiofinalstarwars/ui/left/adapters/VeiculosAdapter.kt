package com.android.desafiofinalstarwars.ui.left.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Vehicle

class VeiculosAdapter() : RecyclerView.Adapter<VeiculosAdapter.ViewHolder>() {

    private val vehicles : MutableList<Vehicle> = mutableListOf()
    lateinit var itemClickListener: (Vehicle) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(vehicle : Vehicle){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            titulo.text = vehicle.name
            subTitulo.text = "Model: " + vehicle.model
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
        holder.vincula(vehicles[position])
    }

    override fun getItemCount() : Int = vehicles.size

    fun atualiza(vehicles : List<Vehicle>){
        this.vehicles.clear()
        this.vehicles.addAll(vehicles)
        notifyDataSetChanged()
    }
}