package com.android.desafiofinalstarwars.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Especie
import com.android.desafiofinalstarwars.model.Planeta

class EspeciesAdapter() : RecyclerView.Adapter<EspeciesAdapter.ViewHolder>() {

    private val especies : MutableList<Especie> = mutableListOf()
    lateinit var itemClickListener: (Especie) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(especie: Especie){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            val subTitulo2 = binding.cardViewSubtitulo2
            titulo.text = especie.nome
            subTitulo.text = "Altura: " + especie.alturaMedia + "cm"
            subTitulo2.text = "Média de Vida: " + especie.mediaDeVida
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(especie)
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

    fun atualiza(especies: List<Especie>){
        this.especies.clear()
        this.especies.addAll(especies)
        notifyDataSetChanged()
    }
}