package com.android.desafiofinalstarwars.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentMoviesBinding
import com.android.desafiofinalstarwars.model.Movie
import com.android.desafiofinalstarwars.ui.DetailsView
import com.android.desafiofinalstarwars.ui.movies.MoviesMainFragment.Companion.onTabReselectedMoviesListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val moviesList : ArrayList<Movie> = ArrayList()

    private val viewModel by viewModel<MoviesViewModel>()

    private val adapter by lazy {
        MoviesAdapter()
    }

    private val recyclerView by lazy {
        binding.fragmentMoviesRecyclerview
    }


    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter

        setObserver()

        viewModel.getApiMovies()

        adapter.itemClickListener = {
            isClicked = 1
            descriptionTabCall(it)
        }
        onTabReselectedMoviesListener = {
            isClicked -= 1
            descriptionTabCall()
        }

    }


    private fun descriptionTabCall(movie: Movie? = null) {
        val viewDetails = binding.fragmentViewDetails.root
        if (isClicked == 1){
            recyclerView.startAnimation(fromVisible)
            recyclerView.visibility = View.GONE
            viewDetails.startAnimation(toVisible)
            viewDetails.visibility = View.VISIBLE
            DetailsView(binding.fragmentViewDetails).bind(movie!!)
        } else if (isClicked == 0) {
            recyclerView.startAnimation(toVisible)
            recyclerView.visibility = View.VISIBLE
            viewDetails.startAnimation(fromVisible)
            viewDetails.visibility = View.GONE
        }
    }

    private fun setObserver(){
        viewModel.movieResponse.observe(viewLifecycleOwner){
            it?.let {
                moviesList.addAll(it.results!!)
                adapter.update(moviesList)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.movieError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: MoviesViewModel.State) {
        when(state){
            MoviesViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            MoviesViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

}