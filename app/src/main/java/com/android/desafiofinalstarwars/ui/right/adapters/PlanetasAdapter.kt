package com.android.desafiofinalstarwars.ui.right.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Planeta

class PlanetasAdapter() : RecyclerView.Adapter<PlanetasAdapter.ViewHolder>() {

    private val planetas : MutableList<Planeta> = mutableListOf()
    lateinit var itemClickListener: (Planeta) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(planeta: Planeta){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            val subTitulo2 = binding.cardViewSubtitulo2
            titulo.text = planeta.nome
            subTitulo.text = "Agua por km³: " + planeta.aguaNaSuperficie
            subTitulo2.text = "População: " + planeta.populacao
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(planeta)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(planetas[position])
    }

    override fun getItemCount() : Int = planetas.size

    fun atualiza(planetas: List<Planeta>){
        this.planetas.clear()
        this.planetas.addAll(planetas)
        notifyDataSetChanged()
    }
}