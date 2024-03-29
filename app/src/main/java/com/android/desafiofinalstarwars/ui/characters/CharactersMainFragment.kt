package com.android.desafiofinalstarwars.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.databinding.FragmentMainCharactersBinding
import com.android.desafiofinalstarwars.ui.characters.adapters.HomeViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CharactersMainFragment : Fragment() {

    private var _binding: FragmentMainCharactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val labels = arrayOf("Characters", "Species")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = HomeViewPagerAdapter(requireActivity())
        binding.fragmentHomeViewpagerTablayout.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.fragmentHomeTablayout, binding.fragmentHomeViewpagerTablayout
        ) {
            tab : TabLayout.Tab, position : Int ->
            tab.text = labels[position]
        }.attach()

        defaultTabSelection()
        setListener()
    }

    override fun onResume() {
        super.onResume()
        defaultTabSelection()
    }

    private fun defaultTabSelection(){
        binding.fragmentHomeViewpagerTablayout.setCurrentItem(0, false)
    }

    private fun setListener(){
        binding.fragmentHomeTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (binding.fragmentHomeViewpagerTablayout.currentItem == 0){
                    onTabReselectedCharactersListener.invoke()
                } else {
                    onTabReselectedSpeciesListener.invoke()
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        lateinit var onTabReselectedCharactersListener : () -> Unit
        lateinit var onTabReselectedSpeciesListener : () -> Unit
    }

}