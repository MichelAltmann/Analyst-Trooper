package com.android.desafiofinalstarwars.ui.left.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentNavesBinding
import com.android.desafiofinalstarwars.model.Especie
import com.android.desafiofinalstarwars.model.Nave
import com.android.desafiofinalstarwars.ui.DetalhesView
import com.android.desafiofinalstarwars.ui.home.HomeFragment
import com.android.desafiofinalstarwars.ui.home.HomeFragment.Companion.onTabReselectedEspeciesListener
import com.android.desafiofinalstarwars.ui.left.LeftFragment
import com.android.desafiofinalstarwars.ui.left.viewmodels.NavesViewModel
import com.android.desafiofinalstarwars.ui.left.adapters.NavesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavesFragment : Fragment() {

    private var _binding: FragmentNavesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModel<NavesViewModel>()

    private val listaNaves : ArrayList<Nave> = ArrayList()

    private val adapter by lazy {
        NavesAdapter()
    }

    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNavesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentNavesRecyclerview.adapter = adapter
        setObserver()
        viewModel.getBuscaNavesApi()

        adapter.itemClickListener = {
            isClicked = 1
            chamaTelaDescricao(it)
        }
        LeftFragment.onTabReselectedNavesListener = {
            isClicked = isClicked -1
            chamaTelaDescricao()
        }

    }

    private fun chamaTelaDescricao(nave: Nave? = null) {
        if (isClicked == 1){
            binding.fragmentNavesRecyclerview.startAnimation(fromVisible)
            binding.fragmentNavesRecyclerview.visibility = View.GONE
            binding.fragmentViewDetalhes.root.startAnimation(toVisible)
            binding.fragmentViewDetalhes.root.visibility = View.VISIBLE
            DetalhesView(binding.fragmentViewDetalhes).bind(nave!!)
        } else if (isClicked == 0) {
            binding.fragmentNavesRecyclerview.startAnimation(toVisible)
            binding.fragmentNavesRecyclerview.visibility = View.VISIBLE
            binding.fragmentViewDetalhes.root.startAnimation(fromVisible)
            binding.fragmentViewDetalhes.root.visibility = View.GONE
        }
    }

    private fun setObserver(){
        viewModel.naveResposta.observe(viewLifecycleOwner){
            it?.let {
                listaNaves.addAll(it.resultados!!)
                adapter.atualiza(listaNaves)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.naveError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: NavesViewModel.State?) {
        when(state){
            NavesViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            NavesViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}