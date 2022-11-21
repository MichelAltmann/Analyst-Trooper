package com.android.desafiofinalstarwars.ui.right.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentFilmesBinding
import com.android.desafiofinalstarwars.model.Movie
import com.android.desafiofinalstarwars.ui.DetalhesView
import com.android.desafiofinalstarwars.ui.right.RightFragment
import com.android.desafiofinalstarwars.ui.right.viewmodel.MoviesViewModel
import com.android.desafiofinalstarwars.ui.right.adapters.FilmesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmesFragment : Fragment() {

    private var _binding: FragmentFilmesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val moviesList : ArrayList<Movie> = ArrayList()

    private val viewModel by viewModel<MoviesViewModel>()

    private val adapter by lazy {
        FilmesAdapter()
    }

    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentFilmesRecyclerview.adapter = adapter

        setObserver()

        viewModel.getBuscaPlanetasApi()

        adapter.itemClickListener = {
            isClicked = 1
            chamaTelaDescricao(it)
        }
        RightFragment.onTabReselectedFilmesListener = {
            isClicked = isClicked -1
            chamaTelaDescricao()
        }

    }

    private fun chamaTelaDescricao(movie: Movie? = null) {
        if (isClicked == 1){
            binding.fragmentFilmesRecyclerview.startAnimation(fromVisible)
            binding.fragmentFilmesRecyclerview.visibility = View.GONE
            binding.fragmentViewDetalhes.root.startAnimation(toVisible)
            binding.fragmentViewDetalhes.root.visibility = View.VISIBLE
            DetalhesView(binding.fragmentViewDetalhes).bind(movie!!)
        } else if (isClicked == 0) {
            binding.fragmentFilmesRecyclerview.startAnimation(toVisible)
            binding.fragmentFilmesRecyclerview.visibility = View.VISIBLE
            binding.fragmentViewDetalhes.root.startAnimation(fromVisible)
            binding.fragmentViewDetalhes.root.visibility = View.GONE
        }
    }

    private fun setObserver(){
        viewModel.movieResponse.observe(viewLifecycleOwner){
            it?.let {
                moviesList.addAll(it.results!!)
                adapter.atualiza(moviesList)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.filmeError.observe(viewLifecycleOwner){
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