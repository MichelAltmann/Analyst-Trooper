package com.android.desafiofinalstarwars.ui.home.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.desafiofinalstarwars.databinding.FragmentPersonagensBinding
import com.android.desafiofinalstarwars.model.Personagem
import com.android.desafiofinalstarwars.ui.home.HomeFragment
import com.android.desafiofinalstarwars.ui.home.viewmodels.PersonagensViewModel
import com.android.desafiofinalstarwars.ui.home.adapters.PersonagensAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonagensFragment : Fragment() {

    private var _binding: FragmentPersonagensBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val listaPersonagens : ArrayList<Personagem> = arrayListOf()

    private val viewModel by viewModel<PersonagensViewModel>()

    private val adapter by lazy {
        PersonagensAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonagensBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentPersonagensRecyclerview.adapter = adapter

        setObserver()

        viewModel.getBuscaPersonagemsApi()

        adapter.itemClickListener = {
            binding.fragmentPersonagensRecyclerview.visibility = View.GONE
            binding.fragmentViewDetalhes.root.visibility = View.VISIBLE
        }

        Log.i(TAG, "onViewCreated: ")
    }

    private fun setObserver() {
        Log.i(TAG, "setObserver: ")
        viewModel.personagemResposta.observe(viewLifecycleOwner){
            it?.let {
                listaPersonagens.addAll(it.resultados!!)
                adapter.atualiza(listaPersonagens)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.personagemError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: PersonagensViewModel.State?) {
        when(state){
            PersonagensViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            PersonagensViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

}