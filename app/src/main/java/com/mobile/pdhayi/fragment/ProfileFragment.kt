package com.mobile.pdhayi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.pdhayi.R
import com.mobile.pdhayi.adapter.ViewPagerAdapter

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPagerProfile)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayoutProfile)

        val adapter = ViewPagerAdapter(requireActivity())
        viewPager.adapter = adapter

        // Attach the ViewPager2 to the TabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.grid).setContentDescription("Posts")  // Your posts icon
                1 -> tab.setIcon(R.drawable.recently_selected).setContentDescription("Activity")  // Your activity icon
            }
        }.attach()

        // Customize tab icon colors and change icons on selection
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> tab.setIcon(R.drawable.grid)  // Set selected icon for posts
                    1 -> tab.setIcon(R.drawable.recently_selected)  // Set selected icon for activity
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> tab.setIcon(R.drawable.post_menu_unselected)  // Set unselected icon for posts
                    1 -> tab.setIcon(R.drawable.recently_unselected)  // Set unselected icon for activity
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Do nothing
            }
        })
    }
}