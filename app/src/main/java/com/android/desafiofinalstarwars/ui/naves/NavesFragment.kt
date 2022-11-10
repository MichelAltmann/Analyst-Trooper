package com.android.desafiofinalstarwars.ui.naves

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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
                Log.e(TAG, "setObserver: " + listaNaves[1].nome)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}