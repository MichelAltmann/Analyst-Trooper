package com.android.desafiofinalstarwars.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import com.android.desafiofinalstarwars.databinding.FragmentViewDetailsBinding
import com.android.desafiofinalstarwars.model.*


class DetailsView(private val binding : FragmentViewDetailsBinding) {

    private val name = binding.textViewName
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

       if (!character.name.isNullOrEmpty()) name.text = character.name
       else name.visibility = View.GONE

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
        if (!specie.name.isNullOrEmpty()) name.text = specie.name
        else name.visibility = View.GONE

        if (!specie.language.isNullOrEmpty()) generic1.text = "Language: "+ specie.language
        else generic1.visibility = View.GONE

        if (!specie.averageHeight.isNullOrEmpty()) generic2.text = "Average Height: "+ specie.averageHeight + "cm"
        else generic2.visibility = View.GONE

        if (!specie.classification.isNullOrEmpty()) generic3.text = "Classification: "+ specie.classification
        else generic3.visibility = View.GONE

        if (!specie.hairColors.isNullOrEmpty()) generic4.text = "Hair Colors: "+ specie.hairColors
        else generic4.visibility = View.GONE

        if (!specie.skinColors.isNullOrEmpty()) generic5.text = "Skin Colors: "+ specie.skinColors
        else generic5.visibility = View.GONE

        if (!specie.eyeColors.isNullOrEmpty()) generic6.text = "Eye Colors: "+ specie.eyeColors
        else generic6.visibility = View.GONE

        if (!specie.averageLifespan.isNullOrEmpty()) generic7.text = "Lifespan: "+ specie.averageLifespan
        else generic7.visibility = View.GONE

        generic8.visibility = View.GONE
        generic9.visibility = View.GONE
        generic10.visibility = View.GONE
        generic11.visibility = View.GONE
        generic12.visibility = View.GONE
    }

    fun bind(starship: Starship){
        if (!starship.name.isNullOrEmpty()) name.text = starship.name
        else name.visibility = View.GONE

        if (!starship.model.isNullOrEmpty()) generic1.text = "Model: "+ starship.model
        else generic1.visibility = View.GONE

        if (!starship.`class`.isNullOrEmpty()) generic2.text = "Class: "+ starship.`class`
        else generic2.visibility = View.GONE

        if (!starship.MGLT.isNullOrEmpty()) generic3.text = "MGLT: "+ starship.MGLT
        else generic3.visibility = View.GONE

        if (!starship.maxSpeed.isNullOrEmpty()) generic4.text = "Max Speed: "+ starship.maxSpeed
        else generic4.visibility = View.GONE

        if (!starship.rating.isNullOrEmpty()) generic5.text = "Hyperdrive Rating: "+ starship.rating
        else generic5.visibility = View.GONE

        if (!starship.length.isNullOrEmpty()) generic6.text = "Lenght: "+ starship.length
        else generic6.visibility = View.GONE

        if (!starship.consumables.isNullOrEmpty()) generic7.text = "Consumables: "+ starship.consumables
        else generic7.visibility = View.GONE

        if (!starship.cargoCapacity.isNullOrEmpty()) generic8.text = "Capacity: " + starship.cargoCapacity
        else generic8.visibility = View.GONE

        if (!starship.crewCapacity.isNullOrEmpty()) generic9.text = "Crew Capacity: " + starship.cargoCapacity
        else generic9.visibility = View.GONE

        if (!starship.price.isNullOrEmpty()) generic10.text = "Price: " + starship.price
        else generic10.visibility = View.GONE

        if (!starship.manufacturer.isNullOrEmpty()) generic11.text = "Manufacturer: " + starship.manufacturer
        else generic11.visibility = View.GONE

        generic12.visibility = View.GONE
    }

    fun bind(vehicle: Vehicle){
        if (!vehicle.name.isNullOrEmpty()) name.text = vehicle.name
        else name.visibility = View.GONE

        if (!vehicle.model.isNullOrEmpty()) generic1.text = "Model: "+ vehicle.model
        else generic1.visibility = View.GONE

        if (!vehicle.vehicleClass.isNullOrEmpty()) generic2.text = "Class: "+ vehicle.vehicleClass
        else generic2.visibility = View.GONE

        if (!vehicle.cargoCapacity.isNullOrEmpty()) generic3.text = "Cargo Capacity: "+ vehicle.cargoCapacity
        else generic3.visibility = View.GONE

        if (!vehicle.maxSpeed.isNullOrEmpty()) generic4.text = "Max Speed: "+ vehicle.maxSpeed
        else generic4.visibility = View.GONE

        if (!vehicle.length.isNullOrEmpty()) generic5.text = "Lenght: "+ vehicle.length
        else generic5.visibility = View.GONE

        if (!vehicle.consumables.isNullOrEmpty()) generic6.text = "Consumables: "+ vehicle.consumables
        else generic6.visibility = View.GONE

        if (!vehicle.passengers.isNullOrEmpty()) generic7.text = "Passengers: " + vehicle.passengers
        else generic7.visibility = View.GONE

        if (!vehicle.crewCapacity.isNullOrEmpty()) generic8.text = "Crew: " + vehicle.crewCapacity
        else generic8.visibility = View.GONE

        if (!vehicle.price.isNullOrEmpty()) generic9.text = "Hyperdrive Rating: "+ vehicle.price
        else generic9.visibility = View.GONE

        if (!vehicle.manufacturer.isNullOrEmpty()) generic10.text = "Manufacturer: " + vehicle.manufacturer
        else generic10.visibility = View.GONE


        generic11.visibility = View.GONE
        generic12.visibility = View.GONE
    }

    fun bind(planet: Planet){
        if (!planet.name.isNullOrEmpty()) name.text = planet.name
        else name.visibility = View.GONE

        if (!planet.diameter.isNullOrEmpty()) generic1.text = "Diameter: "+ planet.diameter
        else generic1.visibility = View.GONE

        if (!planet.surfaceWater.isNullOrEmpty()) generic2.text = "Water Surface: "+ planet.surfaceWater
        else generic2.visibility = View.GONE

        if (!planet.terrain.isNullOrEmpty()) generic3.text = "Terrain Type: "+ planet.terrain
        else generic3.visibility = View.GONE

        if (!planet.gravity.isNullOrEmpty()) generic4.text = "Gravity: "+ planet.gravity
        else generic4.visibility = View.GONE

        if (!planet.rotationPeriod.isNullOrEmpty()) generic5.text = "Rotation Period: "+ planet.rotationPeriod
        else generic5.visibility = View.GONE

        if (!planet.orbitalPeriod.isNullOrEmpty()) generic6.text = "Orbital Period: "+ planet.orbitalPeriod
        else generic6.visibility = View.GONE

        if (!planet.population.isNullOrEmpty()) generic7.text = "Population: " + planet.population
        else generic7.visibility = View.GONE

        generic8.visibility = View.GONE
        generic9.visibility = View.GONE
        generic10.visibility = View.GONE
        generic11.visibility = View.GONE
        generic12.visibility = View.GONE
    }

    fun bind(movie: Movie){
        if (!movie.title.isNullOrEmpty()) name.text = movie.title
        else name.visibility = View.GONE

        if (!movie.openingCrawl.isNullOrEmpty()) generic1.text = movie.openingCrawl
        else generic1.visibility = View.GONE

        if (!movie.releaseDate.isNullOrEmpty()) generic2.text = "Release Date: "+ movie.releaseDate
        else generic2.visibility = View.GONE

        if (!movie.productor.isNullOrEmpty()) generic3.text = "Productor: "+ movie.productor
        else generic3.visibility = View.GONE

        if (!movie.director.isNullOrEmpty()) generic4.text = "Director: "+ movie.director
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