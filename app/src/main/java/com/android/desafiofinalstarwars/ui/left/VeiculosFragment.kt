package com.android.desafiofinalstarwars.ui.left

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.desafiofinalstarwars.databinding.FragmentVeiculosBinding
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.model.Veiculo
import com.android.desafiofinalstarwars.ui.left.adapters.VeiculosAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class VeiculosFragment : Fragment() {

    private var _binding : FragmentVeiculosBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModel<VeiculosViewModel>()

    private val listaVeiculos : ArrayList<Veiculo> = ArrayList()

    private val adapter by lazy {
        VeiculosAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVeiculosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentVeiculosRecyclerview.adapter = adapter
        setObserver()
        viewModel.getBuscaPlanetasApi()
    }

    private fun setObserver() {
        viewModel.veiculoResposta.observe(viewLifecycleOwner){
            it?.let {
                listaVeiculos.addAll(it.resultados!!)
                adapter.atualiza(listaVeiculos)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.veiculoError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: VeiculosViewModel.State) {
        when(state){
            VeiculosViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            VeiculosViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

}