package com.android.desafiofinalstarwars.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Personagem

class PersonagensAdapter() : RecyclerView.Adapter<PersonagensAdapter.ViewHolder>() {

    private val genero = "Gênero: "
    private val personagens : MutableList<Personagem> = mutableListOf()
    lateinit var itemClickListener : (Personagem) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(personagem: Personagem){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            val subTitulo2 = binding.cardViewSubtitulo2
            titulo.text = personagem.nome
            subTitulo.text = "Altura: " + personagem.altura + "cm"
            when(personagem.genero){
                "male" -> subTitulo2.text = genero + "Masculino"
                "female" -> subTitulo2.text = genero + "Feminino"
                else -> subTitulo2.text = genero + "Nenhum"
            }
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(personagem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(personagens[position])
    }

    override fun getItemCount() : Int = personagens.size

    fun atualiza(personagens: List<Personagem>){
        this.personagens.clear()
        this.personagens.addAll(personagens)
        notifyDataSetChanged()
    }
}