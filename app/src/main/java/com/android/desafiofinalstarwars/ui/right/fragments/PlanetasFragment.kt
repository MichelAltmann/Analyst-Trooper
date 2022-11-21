package com.android.desafiofinalstarwars.ui.right.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentPlanetasBinding
import com.android.desafiofinalstarwars.model.Planet
import com.android.desafiofinalstarwars.ui.DetalhesView
import com.android.desafiofinalstarwars.ui.right.RightFragment
import com.android.desafiofinalstarwars.ui.right.viewmodel.PlanetsViewModel
import com.android.desafiofinalstarwars.ui.right.adapters.PlanetasAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlanetasFragment : Fragment() {

    private var _binding: FragmentPlanetasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModel<PlanetsViewModel>()

    private val planetsList : ArrayList<Planet> = ArrayList()

    private val adapter by lazy {
        PlanetasAdapter()
    }

    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentPlanetasRecyclerview.adapter = adapter

        setObserver()

        viewModel.getBuscaPlanetasApi()

        adapter.itemClickListener = {
            isClicked = 1
            chamaTelaDescricao(it)
        }
        RightFragment.onTabReselectedPlanetasListener = {
            isClicked = isClicked -1
            chamaTelaDescricao()
        }

    }

    private fun chamaTelaDescricao(planet: Planet? = null) {
        if (isClicked == 1){
            binding.fragmentPlanetasRecyclerview.startAnimation(fromVisible)
            binding.fragmentPlanetasRecyclerview.visibility = View.GONE
            binding.fragmentViewDetalhes.root.startAnimation(toVisible)
            binding.fragmentViewDetalhes.root.visibility = View.VISIBLE
            DetalhesView(binding.fragmentViewDetalhes).bind(planet!!)
        } else if (isClicked == 0) {
            binding.fragmentPlanetasRecyclerview.startAnimation(toVisible)
            binding.fragmentPlanetasRecyclerview.visibility = View.VISIBLE
            binding.fragmentViewDetalhes.root.startAnimation(fromVisible)
            binding.fragmentViewDetalhes.root.visibility = View.GONE
        }
    }


    private fun setObserver() {
        viewModel.planetResponse.observe(viewLifecycleOwner){
            it?.let {
                planetsList.addAll(it.results!!)
                adapter.atualiza(planetsList)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.planetaError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: PlanetsViewModel.State) {
        when(state){
            PlanetsViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            PlanetsViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}