package com.android.desafiofinalstarwars.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentRightBinding
import com.android.desafiofinalstarwars.databinding.FragmentSearchBinding
import com.android.desafiofinalstarwars.ui.right.adapters.RightViewPagerAdapter
import com.android.desafiofinalstarwars.ui.search.adapters.SearchViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private val viewPagerAdapter by lazy {
        SearchViewPagerAdapter(requireActivity())
    }

    private val labels = arrayOf("Characters","Species","Starships","Vehicles","Planets")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentSearchViewpagerTablayout.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.fragmentSearchTablayout, binding.fragmentSearchViewpagerTablayout
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = labels[position]
        }.attach()

        setListener()
    }

    private fun setListener(){

        binding.fragmentSearchTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        lateinit var onTabSelectedPlanetsListener : () -> Unit
        lateinit var onTabSelectedFilmesListener : () -> Unit
    }
}