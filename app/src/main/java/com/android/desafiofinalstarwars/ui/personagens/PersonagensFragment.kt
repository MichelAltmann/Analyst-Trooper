package com.android.desafiofinalstarwars.ui.personagens

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.desafiofinalstarwars.databinding.FragmentPersonagensBinding
import com.android.desafiofinalstarwars.model.Personagem

class PersonagensFragment : Fragment() {

    private var _binding: FragmentPersonagensBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val listaPersonagens : ArrayList<Personagem> = arrayListOf()

    val personagensViewModel by lazy {
            ViewModelProvider(this).get(PersonagensViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonagensBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personagensViewModel.getBuscaPersonagemsApi()
    }

    private fun setObserver() {
        personagensViewModel.personagemResposta.observe(viewLifecycleOwner){
            listaPersonagens.addAll(it.resultados!!)
            val textView: TextView = binding.textHome
            personagensViewModel.getBuscaPersonagemsApi()
            textView.text = listaPersonagens.get(1).nome
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}