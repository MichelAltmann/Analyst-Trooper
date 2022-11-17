package com.android.desafiofinalstarwars.ui.right

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.desafiofinalstarwars.databinding.FragmentRightBinding
import com.android.desafiofinalstarwars.ui.right.adapters.RightViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class RightFragment : Fragment() {

    private var _binding: FragmentRightBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewPagerAdapter by lazy {
        RightViewPagerAdapter(requireActivity())
    }

    private val labels = arrayOf("Planetas", "Filmes")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentRightViewpagerTablayout.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.activityRightTablayout, binding.fragmentRightViewpagerTablayout
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setText(labels[position])
        }.attach()

        selecionaTabPadrão()
    }

    override fun onResume() {
        super.onResume()
        selecionaTabPadrão()
    }

    private fun selecionaTabPadrão(){
        binding.fragmentRightViewpagerTablayout.setCurrentItem(0, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}