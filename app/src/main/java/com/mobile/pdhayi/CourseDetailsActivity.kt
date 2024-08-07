package com.mobile.pdhayi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.pdhayi.fragment.AboutCourseFragment
import com.mobile.pdhayi.fragment.CurriculumCourseFragment

class CourseDetailsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout_course)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager_course)
        val thumbnail = findViewById<ImageView>(R.id.course_thumbnail)
        Glide.with(this).load("https://www.shutterstock.com/image-vector/3d-web-vector-illustrations-online-600nw-2152289507.jpg").into(thumbnail)

        val adapter = ViewPagerAdapterCourse(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "About"
                1 -> "Curriculum"
                else -> null
            }
        }.attach()

    }
    class ViewPagerAdapterCourse(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        private val fragments = listOf(
            AboutCourseFragment(),
            CurriculumCourseFragment()
        )

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

}

