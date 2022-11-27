package com.android.desafiofinalstarwars.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentRightBinding
import com.android.desafiofinalstarwars.databinding.FragmentSearchBinding
import com.android.desafiofinalstarwars.ui.home.HomeFragment
import com.android.desafiofinalstarwars.ui.home.HomeFragment.Companion.onTabReselectedCharactersListener
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

        searchSetup()
    }

    private fun searchSetup() {
        binding.fragmentSearchSearchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val filter = binding.fragmentSearchSearchBar.text.toString()
                when(binding.fragmentSearchTablayout.selectedTabPosition){
                    0 -> {
                        onTabSelectedCharactersSearchListener.invoke(filter)
                    }
                    1 -> {
                        onTabSelectedSpeciesSearchListener.invoke(filter)
                    }
                    2 -> {
                        onTabSelectedStarshipsSearchListener.invoke(filter)
                    }
                    3 -> {
//                        onTabSelectedVehiclesSearchListener.invoke(filter)
                    }
                    4 -> {
//                        onTabSelectedPlanetsSearchListener.invoke(filter)
                    }
                }
            }

        })
    }

    private fun setListener(){

        binding.fragmentSearchTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0 -> {
                        binding.fragmentSearchSearchBar.hint = "Search for characters"
                        binding.fragmentSearchSearchBar.text.clear()
                    }
                    1 -> {
                        binding.fragmentSearchSearchBar.hint = "Search for species"
                        binding.fragmentSearchSearchBar.text.clear()
                    }
                    2 -> {
                        binding.fragmentSearchSearchBar.hint = "Search for starships"
                        binding.fragmentSearchSearchBar.text.clear()
                    }
                    3 -> {
                        binding.fragmentSearchSearchBar.hint = "Search for vehicles"
                        binding.fragmentSearchSearchBar.text.clear()

                    }
                    4 -> {
                        binding.fragmentSearchSearchBar.hint = "Search for planets"
                        binding.fragmentSearchSearchBar.text.clear()
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


                    }
                    4 -> {

                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (binding.fragmentSearchViewpagerTablayout.currentItem){
                    0 -> onTabReselectedCharactersSearchListener.invoke()
                    1 -> onTabReselectedSpeciesSearchListener.invoke()
                    2 -> onTabReselectedStarshipsSearchListener.invoke()
//                    3 ->
//                    4 ->
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
        lateinit var onTabReselectedSVehiclesSearchListener : () -> Unit
        lateinit var onTabReselectedPlanetsSearchListener : () -> Unit
        lateinit var onTabSelectedCharactersSearchListener : (filter : String) -> Unit
        lateinit var onTabSelectedSpeciesSearchListener : (filter : String) -> Unit
        lateinit var onTabSelectedStarshipsSearchListener : (filter : String) -> Unit
        lateinit var onTabSelectedVehiclesSearchListener : (filter : String) -> Unit
        lateinit var onTabSelectedPlanetsSearchListener : (filter : String) -> Unit
    }
}