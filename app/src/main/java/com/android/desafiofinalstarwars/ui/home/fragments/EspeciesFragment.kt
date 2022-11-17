package com.android.desafiofinalstarwars.ui.home.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentEspeciesBinding
import com.android.desafiofinalstarwars.model.Especie
import com.android.desafiofinalstarwars.ui.DetalhesView
import com.android.desafiofinalstarwars.ui.home.HomeFragment
import com.android.desafiofinalstarwars.ui.home.viewmodels.EspeciesViewModel
import com.android.desafiofinalstarwars.ui.home.adapters.EspeciesAdapter
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

    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    private val adapter by lazy {
        EspeciesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEspeciesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentEspeciesRecyclerview.adapter = adapter

        setObserver()

        viewModel.getBuscaEspeciesApi()
        Log.i(ContentValues.TAG, "onViewCreated: ")

        adapter.itemClickListener = {
            isClicked = 1
            chamaTelaDescricao(it)
        }
        HomeFragment.onTabReselectedEspeciesListener = {
            isClicked = isClicked -1
            chamaTelaDescricao()
        }

    }

    private fun chamaTelaDescricao(especie: Especie? = null) {
        if (isClicked == 1){
            binding.fragmentEspeciesRecyclerview.startAnimation(fromVisible)
            binding.fragmentEspeciesRecyclerview.visibility = View.GONE
            binding.fragmentViewDetalhes.root.startAnimation(toVisible)
            binding.fragmentViewDetalhes.root.visibility = View.VISIBLE
            DetalhesView(binding.fragmentViewDetalhes).bind(especie!!)
        } else if (isClicked == 0) {
            binding.fragmentEspeciesRecyclerview.startAnimation(toVisible)
            binding.fragmentEspeciesRecyclerview.visibility = View.VISIBLE
            binding.fragmentViewDetalhes.root.startAnimation(fromVisible)
            binding.fragmentViewDetalhes.root.visibility = View.GONE
        }
    }

    private fun setObserver() {
        Log.i(ContentValues.TAG, "setObserver: ")
        viewModel.especieResposta.observe(viewLifecycleOwner){
            it?.let {
                listaEspecies.addAll(it.resultados!!)
                adapter.atualiza(listaEspecies)
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