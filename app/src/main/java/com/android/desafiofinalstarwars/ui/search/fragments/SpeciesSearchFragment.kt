package com.android.desafiofinalstarwars.ui.search.fragments

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentSearchSpeciesBinding
import com.android.desafiofinalstarwars.model.Specie
import com.android.desafiofinalstarwars.ui.DetailsView
import com.android.desafiofinalstarwars.ui.characters.adapters.SpeciesAdapter
import com.android.desafiofinalstarwars.ui.characters.fragments.SpeciesFragment
import com.android.desafiofinalstarwars.ui.search.SearchFragment.Companion.onTabReselectedSpeciesSearchListener
import com.android.desafiofinalstarwars.ui.search.SearchFragment.Companion.onTabSelectedSpeciesSearchListener
import com.android.desafiofinalstarwars.ui.search.viewmodels.SpeciesSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SpeciesSearchFragment : Fragment() {
    companion object {
        fun newInstance() = SpeciesFragment()
    }

    private var _binding : FragmentSearchSpeciesBinding? = null

    private val binding get() = _binding!!

    private val speciesList : ArrayList<Specie> = ArrayList()

    private val viewModel by viewModel<SpeciesSearchViewModel>()

    private var isClicked = 0

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    private val adapter by lazy {
        SpeciesAdapter()
    }

    private val recyclerView by lazy{
        binding.fragmentSpeciesSearchRecyclerview
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchSpeciesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()

        setupSearch()
    }

    private fun setupRecycler() {
        recyclerView.adapter = adapter

        setObserver()

        viewModel.getApiSpeciesSearch()
        Log.i(ContentValues.TAG, "onViewCreated: ")

        adapter.itemClickListener = {
            isClicked = 1
            descriptionTabCall(it)
        }
        onTabReselectedSpeciesSearchListener = {
            isClicked -= 1
            descriptionTabCall()
        }
    }

    private fun setupSearch() {
        onTabSelectedSpeciesSearchListener = {
            viewModel.filter = it
            speciesList.clear()
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
                        viewModel.getApiSpeciesSearch()
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

    private fun descriptionTabCall(specie: Specie? = null) {
        val viewDetails = binding.fragmentViewDetails.root
        if (isClicked == 1){
            recyclerView.startAnimation(fromVisible)
            recyclerView.visibility = View.GONE
            viewDetails.startAnimation(toVisible)
            viewDetails.visibility = View.VISIBLE
            DetailsView(binding.fragmentViewDetails).bind(specie!!)
        } else if (isClicked == 0) {
            recyclerView.startAnimation(toVisible)
            recyclerView.visibility = View.VISIBLE
            viewDetails.startAnimation(fromVisible)
            viewDetails.visibility = View.GONE
        }
    }

    private fun setObserver() {
        Log.i(ContentValues.TAG, "setObserver: ")
        viewModel.specieResponse.observe(viewLifecycleOwner){
            it?.let {
                speciesList.addAll(it.results!!)
                adapter.update(speciesList)
                removeScrollListenerAdapter()
                addScrollListenerAdapter(it.next)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
    }

    private fun handleProgressBar(state: SpeciesSearchViewModel.State) {
        when(state){
            SpeciesSearchViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            SpeciesSearchViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }
}
