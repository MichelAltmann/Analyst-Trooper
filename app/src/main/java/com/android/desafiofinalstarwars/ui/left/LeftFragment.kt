package com.android.desafiofinalstarwars.ui.left

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentLeftBinding
import com.android.desafiofinalstarwars.ui.left.adapters.LeftViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class LeftFragment : Fragment() {

    private var _binding : FragmentLeftBinding? = null

    private val binding get() = _binding!!

    private val viewPagerAdapter by lazy {
        LeftViewPagerAdapter(requireActivity())
    }

    private val labels = arrayOf("Naves", "Veículos")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentLeftViewpagerTablayout.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.activityLeftTablayout, binding.fragmentLeftViewpagerTablayout
        ) { tab : TabLayout.Tab, position : Int ->
            tab.setText(labels[position])
        }.attach()

        selecionaTabPadrão()
    }

    override fun onResume() {
        super.onResume()
        selecionaTabPadrão()
    }

    private fun selecionaTabPadrão(){
        binding.fragmentLeftViewpagerTablayout.setCurrentItem(0, false)
    }

}