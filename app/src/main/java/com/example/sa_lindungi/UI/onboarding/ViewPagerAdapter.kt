package com.example.sa_lindungi.UI.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    lists: ArrayList<Fragment>,
    fragmentManagers: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManagers, lifecycle) {
    private val fragmentlist : ArrayList<Fragment> = lists

    override fun getItemCount(): Int {
        return fragmentlist.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentlist[position]
    }
}