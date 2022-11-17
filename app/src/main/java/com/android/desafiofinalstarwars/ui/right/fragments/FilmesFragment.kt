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
import com.android.desafiofinalstarwars.model.Filme
import com.android.desafiofinalstarwars.model.Planeta
import com.android.desafiofinalstarwars.ui.DetalhesView
import com.android.desafiofinalstarwars.ui.right.RightFragment
import com.android.desafiofinalstarwars.ui.right.viewmodel.FilmesViewModel
import com.android.desafiofinalstarwars.ui.right.adapters.FilmesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmesFragment : Fragment() {

    private var _binding: FragmentFilmesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val listaFilmes : ArrayList<Filme> = ArrayList()

    private val viewModel by viewModel<FilmesViewModel>()

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

    private fun chamaTelaDescricao(filme: Filme? = null) {
        if (isClicked == 1){
            binding.fragmentFilmesRecyclerview.startAnimation(fromVisible)
            binding.fragmentFilmesRecyclerview.visibility = View.GONE
            binding.fragmentViewDetalhes.root.startAnimation(toVisible)
            binding.fragmentViewDetalhes.root.visibility = View.VISIBLE
            DetalhesView(binding.fragmentViewDetalhes).bind(filme!!)
        } else if (isClicked == 0) {
            binding.fragmentFilmesRecyclerview.startAnimation(toVisible)
            binding.fragmentFilmesRecyclerview.visibility = View.VISIBLE
            binding.fragmentViewDetalhes.root.startAnimation(fromVisible)
            binding.fragmentViewDetalhes.root.visibility = View.GONE
        }
    }

    private fun setObserver(){
        viewModel.filmeResposta.observe(viewLifecycleOwner){
            it?.let {
                listaFilmes.addAll(it.resultados!!)
                adapter.atualiza(listaFilmes)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.filmeError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: FilmesViewModel.State) {
        when(state){
            FilmesViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            FilmesViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

}