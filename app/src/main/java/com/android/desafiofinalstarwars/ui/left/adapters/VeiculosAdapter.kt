package com.android.desafiofinalstarwars.ui.left.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Veiculo

class VeiculosAdapter() : RecyclerView.Adapter<VeiculosAdapter.ViewHolder>() {

    private val veiculos : MutableList<Veiculo> = mutableListOf()
    lateinit var itemClickListener: (Veiculo) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(veiculo : Veiculo){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            titulo.text = veiculo.nome
            subTitulo.text = "Model: " + veiculo.modelo
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(veiculo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(veiculos[position])
    }

    override fun getItemCount() : Int = veiculos.size

    fun atualiza(veiculos : List<Veiculo>){
        this.veiculos.clear()
        this.veiculos.addAll(veiculos)
        notifyDataSetChanged()
    }
}