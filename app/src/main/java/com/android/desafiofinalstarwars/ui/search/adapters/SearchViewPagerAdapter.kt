package com.android.desafiofinalstarwars.ui.search.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.desafiofinalstarwars.ui.search.fragments.*

class SearchViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return CharacterSearchFragment()
            1 -> return SpecieSearchFragment()
            2 -> return StarshipSearchFragment()
            3 -> return VehicleSearchFragment()
            4 -> return PlanetsSearchFragment()
            else -> return CharacterSearchFragment()
        }
    }

}