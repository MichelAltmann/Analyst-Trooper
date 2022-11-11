package com.android.desafiofinalstarwars.ui.left

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.desafiofinalstarwars.R

class VeiculosFragment : Fragment() {

    companion object {
        fun newInstance() = VeiculosFragment()
    }

    private lateinit var viewModel: VeiculosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_veiculos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VeiculosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}