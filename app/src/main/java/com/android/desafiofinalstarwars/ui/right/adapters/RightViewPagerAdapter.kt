package com.android.desafiofinalstarwars.ui.right.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.desafiofinalstarwars.ui.right.FilmesFragment
import com.android.desafiofinalstarwars.ui.right.PlanetasFragment

class RightViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return PlanetasFragment()
            1 -> return FilmesFragment()
        }
        return PlanetasFragment()
    }

}