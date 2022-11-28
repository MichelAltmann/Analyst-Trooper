package com.android.desafiofinalstarwars.ui.search.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentSearchVehiclesBinding
import com.android.desafiofinalstarwars.model.Vehicle
import com.android.desafiofinalstarwars.ui.DetailsView
import com.android.desafiofinalstarwars.ui.vehicles.adapters.VehiclesAdapter
import com.android.desafiofinalstarwars.ui.search.SearchFragment.Companion.onTabReselectedVehiclesSearchListener
import com.android.desafiofinalstarwars.ui.search.SearchFragment.Companion.onTabSelectedVehiclesSearchListener
import com.android.desafiofinalstarwars.ui.search.viewmodels.VehiclesSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehiclesSearchFragment : Fragment() {
    private var _binding: FragmentSearchVehiclesBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModel<VehiclesSearchViewModel>()

    private val vehiclesList: ArrayList<Vehicle> = ArrayList()

    private val adapter by lazy {
        VehiclesAdapter()
    }

    private val recyclerView by lazy {
        binding.fragmentVehiclesSearchRecyclerview
    }

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private var isClicked = 0

    private val fromVisible: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.fromvisible
        )
    }
    private val toVisible: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.tovisible
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchVehiclesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()

        setupSearch()
    }

    private fun setupRecycler() {
        setObserver()
        recyclerView.adapter = adapter
        viewModel.getApiVehiclesSearch()

        adapter.itemClickListener = {
            isClicked = 1
            descriptionTabCall(it)
        }
        onTabReselectedVehiclesSearchListener = {
            isClicked -= 1
            descriptionTabCall()
        }
    }

    private fun setupSearch() {
        onTabSelectedVehiclesSearchListener = {
            viewModel.filter = it
            vehiclesList.clear()
        }
    }

    private fun addScrollListenerAdapter(isLastPage: String?) {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = layoutManager.childCount
                    val totalItemVisble = visibleItemCount + pastVisibleItems
                    val totalItemCount = layoutManager.itemCount
                    if (totalItemVisble >= totalItemCount && isLastPage != null) {
                        removeScrollListenerAdapter()
                        viewModel.getApiVehiclesSearch()
                    } else if (totalItemVisble >= totalItemCount && isLastPage == null) {
                        Toast.makeText(context, "List end reached.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun removeScrollListenerAdapter() {
        if (::scrollListener.isInitialized) {
            recyclerView.removeOnScrollListener(scrollListener)
        }
    }


    private fun descriptionTabCall(vehicle: Vehicle? = null) {
        val viewDetails = binding.fragmentViewDetails.root
        if (isClicked == 1) {
            recyclerView.startAnimation(fromVisible)
            recyclerView.visibility = View.GONE
            viewDetails.startAnimation(toVisible)
            viewDetails.visibility = View.VISIBLE
            DetailsView(binding.fragmentViewDetails).bind(vehicle!!)
        } else if (isClicked == 0) {
            recyclerView.startAnimation(toVisible)
            recyclerView.visibility = View.VISIBLE
            viewDetails.startAnimation(fromVisible)
            viewDetails.visibility = View.GONE
        }
    }

    private fun setObserver() {
        viewModel.vehicleResponse.observe(viewLifecycleOwner) {
            it?.let {
                vehiclesList.clear()
                vehiclesList.addAll(it.results!!)
                adapter.update(vehiclesList)
                removeScrollListenerAdapter()
                addScrollListenerAdapter(it.next)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner) {
            handleProgressBar(it)
        }
    }

    private fun handleProgressBar(state: VehiclesSearchViewModel.State) {
        when (state) {
            VehiclesSearchViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            VehiclesSearchViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility =
                View.GONE
            else -> {}
        }
    }

}
