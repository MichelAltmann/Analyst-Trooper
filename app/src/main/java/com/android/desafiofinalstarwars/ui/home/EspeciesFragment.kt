package com.android.desafiofinalstarwars.ui.home

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.desafiofinalstarwars.databinding.FragmentEspeciesBinding
import com.android.desafiofinalstarwars.model.Especie
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class EspeciesFragment : Fragment() {

    companion object {
        fun newInstance() = EspeciesFragment()
    }

    private var _binding : FragmentEspeciesBinding? = null

    private val binding get() = _binding!!

    private val listaEspecies : ArrayList<Especie> = ArrayList()

    private val viewModel by viewModel<EspeciesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEspeciesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        viewModel.getBuscaEspeciesApi()
        Log.i(ContentValues.TAG, "onViewCreated: ")
    }

    private fun setObserver() {
        Log.i(ContentValues.TAG, "setObserver: ")
        viewModel.especieResposta.observe(viewLifecycleOwner){
            it?.let {
                listaEspecies.addAll(it.resultados!!)
                binding.textEspecie.text = listaEspecies[1].nome
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.especieError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: EspeciesViewModel.State) {
        when(state){
            EspeciesViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            EspeciesViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

}