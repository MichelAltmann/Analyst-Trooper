package com.android.desafiofinalstarwars.ui.naves

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.databinding.FragmentNavesBinding
import com.android.desafiofinalstarwars.model.Nave
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavesFragment : Fragment() {

    private var _binding: FragmentNavesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModel<NavesViewModel>()

    private val listaNaves : ArrayList<Nave> = ArrayList()
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
        setObserver()
        viewModel.getBuscaNavesApi()
    }

    private fun setObserver(){
        viewModel.naveResposta.observe(viewLifecycleOwner){
            it?.let {
                listaNaves.addAll(it.resultados!!)
                binding.textDashboard.text = listaNaves[1].nome
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