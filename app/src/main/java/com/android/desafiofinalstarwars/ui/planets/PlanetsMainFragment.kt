package com.android.desafiofinalstarwars.ui.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.databinding.FragmentMainPlanetsBinding
import com.android.desafiofinalstarwars.ui.planets.adapters.PlanetsViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class PlanetsMainFragment : Fragment() {

    private var _binding: FragmentMainPlanetsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewPagerAdapter by lazy {
        PlanetsViewPagerAdapter(requireActivity())
    }

    private val labels = arrayOf("Planets")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPlanetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentRightViewpagerTablayout.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.fragmentMainPlanetsTablayout, binding.fragmentRightViewpagerTablayout
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = labels[position]
        }.attach()

        defaultTabSelectior()

        setListener()
    }

    private fun setListener(){

        binding.fragmentMainPlanetsTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (binding.fragmentRightViewpagerTablayout.currentItem == 0){
                    onTabReselectedPlanetsListener.invoke()
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        defaultTabSelectior()
    }

    private fun defaultTabSelectior(){
        binding.fragmentRightViewpagerTablayout.setCurrentItem(0, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        lateinit var onTabReselectedPlanetsListener : () -> Unit
    }

}