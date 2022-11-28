package com.android.desafiofinalstarwars.ui.movies

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.desafiofinalstarwars.ui.movies.MoviesFragment
import com.android.desafiofinalstarwars.ui.planets.fragments.PlanetsFragment

class MoviesViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return MoviesFragment()
        }
        return MoviesFragment()
    }

}