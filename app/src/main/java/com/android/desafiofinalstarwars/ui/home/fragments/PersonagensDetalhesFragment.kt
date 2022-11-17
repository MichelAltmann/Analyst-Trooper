package com.android.desafiofinalstarwars.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentPersonagensDetalhesBinding


class PersonagensDetalhesFragment : Fragment() {

    private var _binding : FragmentPersonagensDetalhesBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonagensDetalhesBinding.inflate(inflater, container, false)
        return binding.root
    }

}