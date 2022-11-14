package com.android.desafiofinalstarwars.ui.left.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Nave

class NavesAdapter() : RecyclerView.Adapter<NavesAdapter.ViewHolder>() {

    private val naves : MutableList<Nave> = mutableListOf()
    lateinit var itemClickListener: (Nave) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(nave : Nave){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            titulo.text = nave.nome
            subTitulo.text = "Modelo: " + nave.modelo
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(nave)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(naves[position])
    }

    override fun getItemCount() : Int = naves.size

    fun atualiza(naves : List<Nave>){
        this.naves.clear()
        this.naves.addAll(naves)
        notifyDataSetChanged()
    }
}