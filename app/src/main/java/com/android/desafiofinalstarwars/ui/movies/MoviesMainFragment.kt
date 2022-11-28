package com.android.desafiofinalstarwars.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.databinding.FragmentMainMoviesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MoviesMainFragment : Fragment() {

    private var _binding: FragmentMainMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewPagerAdapter by lazy {
        MoviesViewPagerAdapter(requireActivity())
    }

    private val labels = arrayOf("Movies")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentRightViewpagerTablayout.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.fragmentMainMoviesTablayout, binding.fragmentRightViewpagerTablayout
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = labels[position]
        }.attach()

        defaultTabSelectior()

        setListener()
    }

    private fun setListener(){

        binding.fragmentMainMoviesTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (binding.fragmentRightViewpagerTablayout.currentItem == 0){
                    onTabReselectedMoviesListener.invoke()
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
        lateinit var onTabReselectedMoviesListener : () -> Unit
    }
}