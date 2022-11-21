package com.android.desafiofinalstarwars.ui.left.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.android.desafiofinalstarwars.databinding.FragmentVehiclesBinding
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.model.Vehicle
import com.android.desafiofinalstarwars.ui.DetailsView
import com.android.desafiofinalstarwars.ui.left.LeftFragment
import com.android.desafiofinalstarwars.ui.left.viewmodels.VehiclesViewModel
import com.android.desafiofinalstarwars.ui.left.adapters.VehiclesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class VeiculosFragment : Fragment() {

    private var _binding : FragmentVehiclesBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModel<VehiclesViewModel>()

    private val vehiclesList : ArrayList<Vehicle> = ArrayList()

    private val adapter by lazy {
        VehiclesAdapter()
    }

    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehiclesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentVehiclesRecyclerview.adapter = adapter
        setObserver()
        viewModel.getPlanetsApi()

        adapter.itemClickListener = {
            isClicked = 1
            descriptionTabCall(it)
        }
        LeftFragment.onTabReselectedVehiclesListener = {
            isClicked -= 1
            descriptionTabCall()
        }

    }

    private fun descriptionTabCall(vehicle: Vehicle? = null) {
        if (isClicked == 1){
            binding.fragmentVehiclesRecyclerview.startAnimation(fromVisible)
            binding.fragmentVehiclesRecyclerview.visibility = View.GONE
            binding.fragmentViewDetails.root.startAnimation(toVisible)
            binding.fragmentViewDetails.root.visibility = View.VISIBLE
            DetailsView(binding.fragmentViewDetails).bind(vehicle!!)
        } else if (isClicked == 0) {
            binding.fragmentVehiclesRecyclerview.startAnimation(toVisible)
            binding.fragmentVehiclesRecyclerview.visibility = View.VISIBLE
            binding.fragmentViewDetails.root.startAnimation(fromVisible)
            binding.fragmentViewDetails.root.visibility = View.GONE
        }
    }


    private fun setObserver() {
        viewModel.vehicleResponse.observe(viewLifecycleOwner){
            it?.let {
                vehiclesList.addAll(it.results!!)
                adapter.update(vehiclesList)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.vehicleError.observe(viewLifecycleOwner){
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