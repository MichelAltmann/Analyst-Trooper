package com.android.desafiofinalstarwars.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Character

class CharactersAdapter() : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private val genero = "Gender: "
    private val personagens : MutableList<Character> = mutableListOf()
    lateinit var itemClickListener : (Character) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun vincula(character: Character){
            val titulo = binding.cardViewTitulo
            val subTitulo = binding.cardViewSubtitulo
            val subTitulo2 = binding.cardViewSubtitulo2
            titulo.text = character.name
            subTitulo.text = "Height: " + character.height
            when(character.gender){
                "male" -> subTitulo2.text = genero + "Male"
                "female" -> subTitulo2.text = genero + "Female"
                else -> subTitulo2.text = genero + "None"
            }
            itemView.rootView.setOnClickListener {
                itemClickListener.invoke(character)
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

    fun atualiza(personagens: List<Character>){
        this.personagens.clear()
        this.personagens.addAll(personagens)
        notifyDataSetChanged()
    }
}