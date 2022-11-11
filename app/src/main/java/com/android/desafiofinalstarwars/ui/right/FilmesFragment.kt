package com.android.desafiofinalstarwars.ui.right

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentFilmesBinding
import com.android.desafiofinalstarwars.databinding.FragmentPlanetasBinding
import com.android.desafiofinalstarwars.model.Filme
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmesFragment : Fragment() {

    private var _binding: FragmentFilmesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val listaFilmes : ArrayList<Filme> = ArrayList()

    private val viewModel by viewModel<FilmesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        viewModel.getBuscaPlanetasApi()
    }

    private fun setObserver(){
        viewModel.filmeResposta.observe(viewLifecycleOwner){
            it?.let {
                listaFilmes.addAll(it.resultados!!)
                binding.textFilmes.text = listaFilmes[1].titulo
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