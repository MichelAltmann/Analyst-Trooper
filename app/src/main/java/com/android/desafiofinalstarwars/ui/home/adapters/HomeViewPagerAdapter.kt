package com.android.desafiofinalstarwars.ui.home.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.desafiofinalstarwars.ui.home.EspeciesFragment
import com.android.desafiofinalstarwars.ui.home.PersonagensFragment

class HomeViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return PersonagensFragment()
            1 -> return EspeciesFragment()
        }
        return PersonagensFragment()
    }

}