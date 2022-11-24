package com.android.desafiofinalstarwars.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.databinding.ItemCardviewBinding
import com.android.desafiofinalstarwars.model.Character

class CharactersAdapter() : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private val gender = "Gender: "
    private val characters : MutableList<Character> = mutableListOf()
    lateinit var itemClickListener : (Character) -> Unit

    inner class ViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character){
            val title = binding.cardViewTitle
            val subTitle = binding.cardViewSubtitle
            val subTitle2 = binding.cardViewSubtitle2
            title.text = character.name
            subTitle.text = "Height: " + character.height
            when(character.gender){
                "male" -> subTitle2.text = gender + "Male"
                "female" -> subTitle2.text = gender + "Female"
                else -> subTitle2.text = gender + "None"
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
        holder.bind(characters[position])
    }

    override fun getItemCount() : Int = characters.size

    fun update(characters: List<Character>){
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }
}