package com.android.desafiofinalstarwars.ui.vehicles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.desafiofinalstarwars.databinding.FragmentMainVehiclesBinding
import com.android.desafiofinalstarwars.ui.vehicles.adapters.LeftViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class VehiclesMainFragment : Fragment() {

    private var _binding : FragmentMainVehiclesBinding? = null

    private val binding get() = _binding!!

    private val viewPagerAdapter by lazy {
        LeftViewPagerAdapter(requireActivity())
    }

    private val labels = arrayOf("Starships", "Vehicles")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainVehiclesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentLeftViewpagerTablayout.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.fragmentMainVehiclesTablayout, binding.fragmentLeftViewpagerTablayout
        ) { tab : TabLayout.Tab, position : Int ->
            tab.text = labels[position]
        }.attach()

        defaultTabSelector()

        setListener()
    }

    private fun setListener(){
        binding.fragmentMainVehiclesTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (binding.fragmentLeftViewpagerTablayout.currentItem == 0){
                    onTabReselectedStarshipsListener.invoke()
                } else {
                    onTabReselectedVehiclesListener.invoke()
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        defaultTabSelector()
    }

    private fun defaultTabSelector(){
        binding.fragmentLeftViewpagerTablayout.setCurrentItem(0, false)
    }

    companion object {
        lateinit var onTabReselectedStarshipsListener : () -> Unit
        lateinit var onTabReselectedVehiclesListener : () -> Unit
    }
}