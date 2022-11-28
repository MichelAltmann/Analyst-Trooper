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
import com.android.desafiofinalstarwars.databinding.FragmentPlanetsBinding
import com.android.desafiofinalstarwars.databinding.FragmentSearchPlanetsBinding
import com.android.desafiofinalstarwars.model.Planet
import com.android.desafiofinalstarwars.ui.DetailsView
import com.android.desafiofinalstarwars.ui.right.adapters.PlanetsAdapter
import com.android.desafiofinalstarwars.ui.right.viewmodel.PlanetsViewModel
import com.android.desafiofinalstarwars.ui.search.SearchFragment.Companion.onTabReselectedPlanetsSearchListener
import com.android.desafiofinalstarwars.ui.search.SearchFragment.Companion.onTabSelectedPlanetsSearchListener
import com.android.desafiofinalstarwars.ui.search.viewmodels.PlanetsSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlanetsSearchFragment : Fragment() {
    private var _binding: FragmentSearchPlanetsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModel<PlanetsSearchViewModel>()

    private val planetsList : ArrayList<Planet> = ArrayList()

    private val adapter by lazy {
        PlanetsAdapter()
    }

    private val recyclerView by lazy {
        binding.fragmentPlanetsSearchRecyclerview
    }

    private lateinit var scrollListener : RecyclerView.OnScrollListener

    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchPlanetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()

        setupSearch()
    }

    private fun setupSearch() {
        onTabSelectedPlanetsSearchListener = {
            planetsList.clear()
            viewModel.filter = it
        }
    }

    private fun setupRecycler() {
        recyclerView.adapter = adapter

        setObserver()

        viewModel.getApiPlanetsSearch()

        adapter.itemClickListener = {
            isClicked = 1
            descriptionTabCall(it)
        }
        onTabReselectedPlanetsSearchListener = {
            isClicked -= 1
            descriptionTabCall()
        }
    }

    private fun addScrollListenerAdapter(isLastPage : String?) {
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
                        viewModel.getApiPlanetsSearch()
                    } else if (totalItemVisble >= totalItemCount && isLastPage == null){
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


    private fun descriptionTabCall(planet: Planet? = null) {
        val viewDetails = binding.fragmentViewDetails.root
        if (isClicked == 1){
            recyclerView.startAnimation(fromVisible)
            recyclerView.visibility = View.GONE
            viewDetails.startAnimation(toVisible)
            viewDetails.visibility = View.VISIBLE
            DetailsView(binding.fragmentViewDetails).bind(planet!!)
        } else if (isClicked == 0) {
            recyclerView.startAnimation(toVisible)
            recyclerView.visibility = View.VISIBLE
            viewDetails.startAnimation(fromVisible)
            viewDetails.visibility = View.GONE
        }
    }


    private fun setObserver() {
        viewModel.planetResponse.observe(viewLifecycleOwner){
            it?.let {
                planetsList.addAll(it.results!!)
                adapter.update(planetsList)
                removeScrollListenerAdapter()
                addScrollListenerAdapter(it.next)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.planetError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: PlanetsSearchViewModel.State) {
        when(state){
            PlanetsSearchViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            PlanetsSearchViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
