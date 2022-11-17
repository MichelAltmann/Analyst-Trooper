package com.android.desafiofinalstarwars.ui.right.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.databinding.FragmentPlanetasBinding
import com.android.desafiofinalstarwars.model.Planeta
import com.android.desafiofinalstarwars.ui.right.viewmodel.PlanetasViewModel
import com.android.desafiofinalstarwars.ui.right.adapters.PlanetasAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlanetasFragment : Fragment() {

    private var _binding: FragmentPlanetasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModel<PlanetasViewModel>()

    private val listaPlanetas : ArrayList<Planeta> = ArrayList()

    private val adapter by lazy {
        PlanetasAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentPlanetasRecyclerview.adapter = adapter

        setObserver()

        viewModel.getBuscaPlanetasApi()
    }

    private fun setObserver() {
        viewModel.planetaResposta.observe(viewLifecycleOwner){
            it?.let {
                listaPlanetas.addAll(it.resultados!!)
                adapter.atualiza(listaPlanetas)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.planetaError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: PlanetasViewModel.State) {
        when(state){
            PlanetasViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            PlanetasViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}