package com.android.desafiofinalstarwars.ui.vehicles.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.desafiofinalstarwars.ui.vehicles.fragments.StarshipsFragment
import com.android.desafiofinalstarwars.ui.vehicles.fragments.VehiclesFragment

class LeftViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return VehiclesFragment()
            1 -> return StarshipsFragment()
        }
        return StarshipsFragment()
    }

}