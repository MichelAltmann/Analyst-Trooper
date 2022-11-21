package com.android.desafiofinalstarwars.ui.left.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.android.desafiofinalstarwars.databinding.FragmentVeiculosBinding
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.model.Vehicle
import com.android.desafiofinalstarwars.ui.DetalhesView
import com.android.desafiofinalstarwars.ui.left.LeftFragment
import com.android.desafiofinalstarwars.ui.left.viewmodels.VehiclesViewModel
import com.android.desafiofinalstarwars.ui.left.adapters.VeiculosAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class VeiculosFragment : Fragment() {

    private var _binding : FragmentVeiculosBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModel<VehiclesViewModel>()

    private val vehiclesList : ArrayList<Vehicle> = ArrayList()

    private val adapter by lazy {
        VeiculosAdapter()
    }

    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVeiculosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentVeiculosRecyclerview.adapter = adapter
        setObserver()
        viewModel.getBuscaPlanetasApi()

        adapter.itemClickListener = {
            isClicked = 1
            chamaTelaDescricao(it)
        }
        LeftFragment.onTabReselectedVeiculosListener = {
            isClicked -= 1
            chamaTelaDescricao()
        }

    }

    private fun chamaTelaDescricao(vehicle: Vehicle? = null) {
        if (isClicked == 1){
            binding.fragmentVeiculosRecyclerview.startAnimation(fromVisible)
            binding.fragmentVeiculosRecyclerview.visibility = View.GONE
            binding.fragmentViewDetalhes.root.startAnimation(toVisible)
            binding.fragmentViewDetalhes.root.visibility = View.VISIBLE
            DetalhesView(binding.fragmentViewDetalhes).bind(vehicle!!)
        } else if (isClicked == 0) {
            binding.fragmentVeiculosRecyclerview.startAnimation(toVisible)
            binding.fragmentVeiculosRecyclerview.visibility = View.VISIBLE
            binding.fragmentViewDetalhes.root.startAnimation(fromVisible)
            binding.fragmentViewDetalhes.root.visibility = View.GONE
        }
    }


    private fun setObserver() {
        viewModel.veiculoResposta.observe(viewLifecycleOwner){
            it?.let {
                vehiclesList.addAll(it.resultados!!)
                adapter.atualiza(vehiclesList)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.veiculoError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: VehiclesViewModel.State) {
        when(state){
            VehiclesViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            VehiclesViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

}