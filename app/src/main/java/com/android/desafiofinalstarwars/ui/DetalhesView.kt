package com.android.desafiofinalstarwars.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import com.android.desafiofinalstarwars.databinding.FragmentViewDetalhesBinding
import com.android.desafiofinalstarwars.model.*


class DetalhesView(private val binding : FragmentViewDetalhesBinding) {

//    constructor(personagem: Personagem) : this(){
//
//    }

    private val nome = binding.textViewNome
    private val generic1 = binding.textViewInfoGeneric1
    private val generic2 = binding.textViewInfoGeneric2
    private val generic3 = binding.textViewInfoGeneric3
    private val generic4 = binding.textViewInfoGeneric4
    private val generic5 = binding.textViewInfoGeneric5
    private val generic6 = binding.textViewInfoGeneric6
    private val generic7 = binding.textViewInfoGeneric7
    private val generic8 = binding.textViewInfoGeneric8
    private val generic9 = binding.textViewInfoGeneric9
    private val generic10 = binding.textViewInfoGeneric10
    private val generic11 = binding.textViewInfoGeneric11
    private val generic12 = binding.textViewInfoGeneric12

   @SuppressLint("SetTextI18n")
   fun bind(character: Character){
       Log.i(TAG, "bind: " + character.name)

       if (!character.name.isNullOrEmpty()) nome.text = character.name
       else nome.visibility = View.GONE

       if (!character.gender.isNullOrEmpty()) generic1.text = "Gender: "+ character.gender
       else generic1.visibility = View.GONE

       if (!character.height.isNullOrEmpty()) generic2.text = "Height: "+ character.height + "cm"
       else generic2.visibility = View.GONE

       if (!character.birthYear.isNullOrEmpty()) generic3.text = "Year of Birth: "+ character.birthYear
       else generic3.visibility = View.GONE

       if (!character.mass.isNullOrEmpty()) generic4.text = "Weight: "+ character.mass + "Kg"
       else generic4.visibility = View.GONE

       if (!character.skinColor.isNullOrEmpty()) generic5.text = "Skin Color: "+ character.skinColor
       else generic5.visibility = View.GONE

       if (!character.hairColor.isNullOrEmpty()) generic6.text = "Hair Color: "+ character.hairColor
       else generic6.visibility = View.GONE

       if (!character.eyeColor.isNullOrEmpty()) generic7.text = "Eye Color: "+ character.eyeColor
       else generic7.visibility = View.GONE

       generic8.visibility = View.GONE
       generic9.visibility = View.GONE
       generic10.visibility = View.GONE
       generic11.visibility = View.GONE
       generic12.visibility = View.GONE
   }

    fun bind(specie: Specie){
        if (!specie.nome.isNullOrEmpty()) nome.text = specie.nome
        else nome.visibility = View.GONE

        if (!specie.lingua.isNullOrEmpty()) generic1.text = "Language: "+ specie.lingua
        else generic1.visibility = View.GONE

        if (!specie.alturaMedia.isNullOrEmpty()) generic2.text = "Average Height: "+ specie.alturaMedia + "cm"
        else generic2.visibility = View.GONE

        if (!specie.classificacao.isNullOrEmpty()) generic3.text = "Classification: "+ specie.classificacao
        else generic3.visibility = View.GONE

        if (!specie.coresDeCabelo.isNullOrEmpty()) generic4.text = "Hair Colors: "+ specie.coresDeCabelo
        else generic4.visibility = View.GONE

        if (!specie.coresDePele.isNullOrEmpty()) generic5.text = "Skin Colors: "+ specie.coresDePele
        else generic5.visibility = View.GONE

        if (!specie.coresDeOlho.isNullOrEmpty()) generic6.text = "Eye Colors: "+ specie.coresDeOlho
        else generic6.visibility = View.GONE

        if (!specie.mediaDeVida.isNullOrEmpty()) generic7.text = "Lifespan: "+ specie.mediaDeVida
        else generic7.visibility = View.GONE

        generic8.visibility = View.GONE
        generic9.visibility = View.GONE
        generic10.visibility = View.GONE
        generic11.visibility = View.GONE
        generic12.visibility = View.GONE
    }

    fun bind(starship: Starship){
        if (!starship.nome.isNullOrEmpty()) nome.text = starship.nome
        else nome.visibility = View.GONE

        if (!starship.modelo.isNullOrEmpty()) generic1.text = "Model: "+ starship.modelo
        else generic1.visibility = View.GONE

        if (!starship.classe.isNullOrEmpty()) generic2.text = "Class: "+ starship.classe
        else generic2.visibility = View.GONE

        if (!starship.MGLT.isNullOrEmpty()) generic3.text = "MGLT: "+ starship.MGLT
        else generic3.visibility = View.GONE

        if (!starship.velocidadeMaxima.isNullOrEmpty()) generic4.text = "Max Speed: "+ starship.velocidadeMaxima
        else generic4.visibility = View.GONE

        if (!starship.avaliacao.isNullOrEmpty()) generic5.text = "Hyperdrive Rating: "+ starship.avaliacao
        else generic5.visibility = View.GONE

        if (!starship.comprimento.isNullOrEmpty()) generic6.text = "Lenght: "+ starship.comprimento
        else generic6.visibility = View.GONE

        if (!starship.consumiveis.isNullOrEmpty()) generic7.text = "Consumables: "+ starship.consumiveis
        else generic7.visibility = View.GONE

        if (!starship.capacidade.isNullOrEmpty()) generic8.text = "Capacity: " + starship.capacidade
        else generic8.visibility = View.GONE

        if (!starship.capacidadeDePessoas.isNullOrEmpty()) generic9.text = "Crew Capacity: " + starship.capacidade
        else generic9.visibility = View.GONE

        if (!starship.preco.isNullOrEmpty()) generic10.text = "Price: " + starship.preco
        else generic10.visibility = View.GONE

        if (!starship.manufatorador.isNullOrEmpty()) generic11.text = "Manufacturer: " + starship.manufatorador
        else generic11.visibility = View.GONE

        generic12.visibility = View.GONE
    }

    fun bind(veiculo: Veiculo){
        if (!veiculo.nome.isNullOrEmpty()) nome.text = veiculo.nome
        else nome.visibility = View.GONE

        if (!veiculo.modelo.isNullOrEmpty()) generic1.text = "Model: "+ veiculo.modelo
        else generic1.visibility = View.GONE

        if (!veiculo.classe.isNullOrEmpty()) generic2.text = "Class: "+ veiculo.classe
        else generic2.visibility = View.GONE

        if (!veiculo.capacidadeDeCarga.isNullOrEmpty()) generic3.text = "Cargo Capacity: "+ veiculo.capacidadeDeCarga
        else generic3.visibility = View.GONE

        if (!veiculo.velocidadeMaxima.isNullOrEmpty()) generic4.text = "Max Speed: "+ veiculo.velocidadeMaxima
        else generic4.visibility = View.GONE

        if (!veiculo.comprimento.isNullOrEmpty()) generic5.text = "Lenght: "+ veiculo.comprimento
        else generic5.visibility = View.GONE

        if (!veiculo.consumiveis.isNullOrEmpty()) generic6.text = "Consumables: "+ veiculo.consumiveis
        else generic6.visibility = View.GONE

        if (!veiculo.passageiros.isNullOrEmpty()) generic7.text = "Passengers: " + veiculo.passageiros
        else generic7.visibility = View.GONE

        if (!veiculo.tripulantes.isNullOrEmpty()) generic8.text = "Crew: " + veiculo.tripulantes
        else generic8.visibility = View.GONE

        if (!veiculo.preco.isNullOrEmpty()) generic9.text = "Hyperdrive Rating: "+ veiculo.preco
        else generic9.visibility = View.GONE

        if (!veiculo.manufaturador.isNullOrEmpty()) generic10.text = "Manufacturer: " + veiculo.manufaturador
        else generic10.visibility = View.GONE


        generic11.visibility = View.GONE
        generic12.visibility = View.GONE
    }

    fun bind(planeta: Planeta){
        if (!planeta.nome.isNullOrEmpty()) nome.text = planeta.nome
        else nome.visibility = View.GONE

        if (!planeta.diametro.isNullOrEmpty()) generic1.text = "Diameter: "+ planeta.diametro
        else generic1.visibility = View.GONE

        if (!planeta.aguaNaSuperficie.isNullOrEmpty()) generic2.text = "Water Surface: "+ planeta.aguaNaSuperficie
        else generic2.visibility = View.GONE

        if (!planeta.terreno.isNullOrEmpty()) generic3.text = "Terrain Type: "+ planeta.terreno
        else generic3.visibility = View.GONE

        if (!planeta.gravidade.isNullOrEmpty()) generic4.text = "Gravity: "+ planeta.gravidade
        else generic4.visibility = View.GONE

        if (!planeta.periodoDeRotacao.isNullOrEmpty()) generic5.text = "Rotation Period: "+ planeta.periodoDeRotacao
        else generic5.visibility = View.GONE

        if (!planeta.periodoOrbital.isNullOrEmpty()) generic6.text = "Orbital Period: "+ planeta.periodoOrbital
        else generic6.visibility = View.GONE

        if (!planeta.populacao.isNullOrEmpty()) generic7.text = "Population: " + planeta.populacao
        else generic7.visibility = View.GONE

        generic8.visibility = View.GONE
        generic9.visibility = View.GONE
        generic10.visibility = View.GONE
        generic11.visibility = View.GONE
        generic12.visibility = View.GONE
    }

    fun bind(filme: Filme){
        if (!filme.titulo.isNullOrEmpty()) nome.text = filme.titulo
        else nome.visibility = View.GONE

        if (!filme.fraseAbertura.isNullOrEmpty()) generic1.text = filme.fraseAbertura
        else generic1.visibility = View.GONE

        if (!filme.dataLancamento.isNullOrEmpty()) generic2.text = "Release Date: "+ filme.dataLancamento
        else generic2.visibility = View.GONE

        if (!filme.produtor.isNullOrEmpty()) generic3.text = "Productor: "+ filme.produtor
        else generic3.visibility = View.GONE

        if (!filme.diretor.isNullOrEmpty()) generic4.text = "Director: "+ filme.diretor
        else generic4.visibility = View.GONE

        generic5.visibility = View.GONE
        generic6.visibility = View.GONE
        generic7.visibility = View.GONE
        generic8.visibility = View.GONE
        generic9.visibility = View.GONE
        generic10.visibility = View.GONE
        generic11.visibility = View.GONE
        generic12.visibility = View.GONE
    }

}