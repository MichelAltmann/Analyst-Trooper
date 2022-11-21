package com.android.desafiofinalstarwars.ui.home.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentSpeciesBinding
import com.android.desafiofinalstarwars.model.Specie
import com.android.desafiofinalstarwars.ui.DetalhesView
import com.android.desafiofinalstarwars.ui.home.HomeFragment
import com.android.desafiofinalstarwars.ui.home.adapters.SpeciesAdapter
import com.android.desafiofinalstarwars.ui.home.viewmodels.SpeciesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpeciesFragment : Fragment() {

    companion object {
        fun newInstance() = SpeciesFragment()
    }

    private var _binding : FragmentSpeciesBinding? = null

    private val binding get() = _binding!!

    private val speciesList : ArrayList<Specie> = ArrayList()

    private val viewModel by viewModel<SpeciesViewModel>()

    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    private val adapter by lazy {
        SpeciesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpeciesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentSpeciesRecyclerview.adapter = adapter

        setObserver()

        viewModel.getApiSpecies()
        Log.i(ContentValues.TAG, "onViewCreated: ")

        adapter.itemClickListener = {
            isClicked = 1
            descriptionTabCall(it)
        }
        HomeFragment.onTabReselectedSpeciesListener = {
            isClicked -= 1
            descriptionTabCall()
        }

    }

    private fun descriptionTabCall(specie: Specie? = null) {
        if (isClicked == 1){
            binding.fragmentSpeciesRecyclerview.startAnimation(fromVisible)
            binding.fragmentSpeciesRecyclerview.visibility = View.GONE
            binding.fragmentViewDetails.root.startAnimation(toVisible)
            binding.fragmentViewDetails.root.visibility = View.VISIBLE
            DetalhesView(binding.fragmentViewDetails).bind(specie!!)
        } else if (isClicked == 0) {
            binding.fragmentSpeciesRecyclerview.startAnimation(toVisible)
            binding.fragmentSpeciesRecyclerview.visibility = View.VISIBLE
            binding.fragmentViewDetails.root.startAnimation(fromVisible)
            binding.fragmentViewDetails.root.visibility = View.GONE
        }
    }

    private fun setObserver() {
        Log.i(ContentValues.TAG, "setObserver: ")
        viewModel.specieResponse.observe(viewLifecycleOwner){
            it?.let {
                speciesList.addAll(it.results!!)
                adapter.update(speciesList)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.specieError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: SpeciesViewModel.State) {
        when(state){
            SpeciesViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            SpeciesViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

}