package com.android.desafiofinalstarwars.ui.left.fragments

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
import com.android.desafiofinalstarwars.databinding.FragmentStarshipsBinding
import com.android.desafiofinalstarwars.model.Starship
import com.android.desafiofinalstarwars.ui.DetailsView
import com.android.desafiofinalstarwars.ui.left.LeftFragment
import com.android.desafiofinalstarwars.ui.left.viewmodels.StarshipsViewModel
import com.android.desafiofinalstarwars.ui.left.adapters.StarshipsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarshipsFragment : Fragment() {

    private var _binding: FragmentStarshipsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModel<StarshipsViewModel>()

    private val starshipsList : ArrayList<Starship> = ArrayList()

    private val adapter by lazy {
        StarshipsAdapter()
    }

    private val recyclerView by lazy{
        binding.fragmentStarshipsRecyclerview
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

        _binding = FragmentStarshipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    private fun setupRecycler() {
        recyclerView.adapter = adapter

        setObserver()

        viewModel.getApiStarships()

        adapter.itemClickListener = {
            isClicked = 1
            descriptionTabCall(it)
        }
        LeftFragment.onTabReselectedStarshipsListener = {
            isClicked -= 1
            descriptionTabCall()
        }
    }

    private fun addScrollListenerAdapter() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = layoutManager.childCount
                    val totalItemVisble = visibleItemCount + pastVisibleItems
                    val totalItemCount = layoutManager.itemCount
                    if (totalItemVisble >= totalItemCount) {
                        removeScrollListenerAdapter()
                        viewModel.getApiStarships()
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

    private fun descriptionTabCall(starship: Starship? = null) {
        val viewDetail = binding.fragmentViewDetails.root
        if (isClicked == 1){
            recyclerView.startAnimation(fromVisible)
            recyclerView.visibility = View.GONE
            viewDetail.startAnimation(toVisible)
            viewDetail.visibility = View.VISIBLE
            DetailsView(binding.fragmentViewDetails).bind(starship!!)
        } else if (isClicked == 0) {
            recyclerView.startAnimation(toVisible)
            recyclerView.visibility = View.VISIBLE
            viewDetail.startAnimation(fromVisible)
            viewDetail.visibility = View.GONE
        }
    }

    private fun setObserver(){
        viewModel.starshipResponse.observe(viewLifecycleOwner){
            it?.let {
                starshipsList.addAll(it.results!!)
                adapter.update(starshipsList)
                removeScrollListenerAdapter()
                addScrollListenerAdapter()
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.starshipError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Fim da lista.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: StarshipsViewModel.State?) {
        when(state){
            StarshipsViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            StarshipsViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}