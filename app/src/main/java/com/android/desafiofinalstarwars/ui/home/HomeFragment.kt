package com.android.desafiofinalstarwars.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.databinding.FragmentHomeBinding
import com.android.desafiofinalstarwars.databinding.FragmentPersonagensBinding
import com.android.desafiofinalstarwars.model.Personagem
import com.android.desafiofinalstarwars.ui.home.adapters.HomeViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val labels = arrayOf("Personagens", "Espécies")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = HomeViewPagerAdapter(requireActivity())
        binding.fragmentHomeViewpagerTablayout.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.activityHomeTablayout, binding.fragmentHomeViewpagerTablayout
        ) {
            tab : TabLayout.Tab, position : Int ->
            tab.setText(labels[position])
        }.attach()

        selecionaTabPadrão()
    }

    override fun onResume() {
        super.onResume()
        selecionaTabPadrão()
    }

    private fun selecionaTabPadrão(){
        binding.fragmentHomeViewpagerTablayout.setCurrentItem(0, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}