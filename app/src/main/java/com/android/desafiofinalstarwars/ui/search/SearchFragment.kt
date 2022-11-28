package com.android.desafiofinalstarwars.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.android.desafiofinalstarwars.databinding.FragmentSearchBinding
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

        searchSetup()
    }

    private fun searchSetup() {

        binding.fragmentSearchSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.fragmentSearchSearchBar.clearFocus()
                return false
            }

            override fun onQueryTextChange(filter: String?): Boolean {
                if (!filter.isNullOrEmpty()){
                    when(binding.fragmentSearchTablayout.selectedTabPosition){
                        0 -> {
                            onTabSelectedCharactersSearchListener.invoke(filter.orEmpty())
                        }
                        1 -> {
                            onTabSelectedSpeciesSearchListener.invoke(filter.orEmpty())
                        }
                        2 -> {
                            onTabSelectedStarshipsSearchListener.invoke(filter.orEmpty())
                        }
                        3 -> {
                            onTabSelectedVehiclesSearchListener.invoke(filter.orEmpty())
                        }
                        4 -> {
                            onTabSelectedPlanetsSearchListener.invoke(filter.orEmpty())
                        }
                    }
                }
                return false
            }
        })
    }

    private fun setListener(){

        binding.fragmentSearchTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0 -> {
                        binding.fragmentSearchSearchBar.queryHint = "Search for characters"
                        binding.fragmentSearchSearchBar.setQuery("", false)
                    }
                    1 -> {
                        binding.fragmentSearchSearchBar.queryHint = "Search for species"
                        binding.fragmentSearchSearchBar.setQuery("", false)
                    }
                    2 -> {
                        binding.fragmentSearchSearchBar.queryHint = "Search for starships"
                        binding.fragmentSearchSearchBar.setQuery("", true)
                    }
                    3 -> {
                        binding.fragmentSearchSearchBar.queryHint = "Search for vehicles"
                        binding.fragmentSearchSearchBar.setQuery("", false)
                    }
                    4 -> {
                        binding.fragmentSearchSearchBar.queryHint = "Search for planets"
                        binding.fragmentSearchSearchBar.setQuery("", false)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0 -> {
                        onTabSelectedCharactersSearchListener.invoke("")
                    }
                    1 -> {
                        onTabSelectedSpeciesSearchListener.invoke("")
                    }
                    2 -> {
                        onTabSelectedStarshipsSearchListener.invoke("")
                    }
                    3 -> {
                        onTabSelectedVehiclesSearchListener.invoke("")
                    }
                    4 -> {
                        onTabSelectedPlanetsSearchListener.invoke("")
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (binding.fragmentSearchViewpagerTablayout.currentItem){
                    0 -> onTabReselectedCharactersSearchListener.invoke()
                    1 -> onTabReselectedSpeciesSearchListener.invoke()
                    2 -> onTabReselectedStarshipsSearchListener.invoke()
                    3 -> onTabReselectedVehiclesSearchListener.invoke()
                    4 -> onTabReselectedPlanetsSearchListener.invoke()
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        lateinit var onTabReselectedCharactersSearchListener : () -> Unit
        lateinit var onTabReselectedSpeciesSearchListener : () -> Unit
        lateinit var onTabReselectedStarshipsSearchListener : () -> Unit
        lateinit var onTabReselectedVehiclesSearchListener : () -> Unit
        lateinit var onTabReselectedPlanetsSearchListener : () -> Unit
        lateinit var onTabSelectedCharactersSearchListener : (filter : String) -> Unit
        lateinit var onTabSelectedSpeciesSearchListener : (filter : String) -> Unit
        lateinit var onTabSelectedStarshipsSearchListener : (filter : String) -> Unit
        lateinit var onTabSelectedVehiclesSearchListener : (filter : String) -> Unit
        lateinit var onTabSelectedPlanetsSearchListener : (filter : String) -> Unit
    }
}