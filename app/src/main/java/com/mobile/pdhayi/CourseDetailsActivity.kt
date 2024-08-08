package com.mobile.pdhayi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
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
        val category = findViewById<TextView>(R.id.course_details_category)
        val courseNameView = findViewById<TextView>(R.id.course_name_details)
        val priceView = findViewById<TextView>(R.id.price_course_details)
        val rateView = findViewById<TextView>(R.id.course_rating_details)

        val courseImage = intent.getStringExtra("courseImage")
        val courseCategory = intent.getStringExtra("courseCategory")
        val courseName = intent.getStringExtra("courseName")
        val coursePrice = intent.getStringExtra("coursePrice")
        val courseRating = intent.getStringExtra("courseRating")
        val courseStd = intent.getStringExtra("courseStd")
        Glide.with(this).load(courseImage).into(thumbnail)
        category.text = courseCategory
        courseNameView.text = courseName
        priceView.text = coursePrice
        rateView.text = courseRating

        val adapter = ViewPagerAdapterCourse(
            this,
            courseImage,
            courseCategory,
            courseName,
            coursePrice,
            courseRating,
            courseStd
        )
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "About"
                1 -> "Curriculum"
                else -> null
            }
        }.attach()
    }

    class ViewPagerAdapterCourse(
        fragmentActivity: FragmentActivity,
        private val courseImage: String?,
        private val courseCategory: String?,
        private val courseName: String?,
        private val coursePrice: String?,
        private val courseRating: String?,
        private val courseStd: String?
    ) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            val fragment = when (position) {
                0 -> AboutCourseFragment()
                1 -> CurriculumCourseFragment()
                else -> throw IllegalStateException("Unexpected position $position")
            }
            fragment.arguments = Bundle().apply {
                putString("courseImage", courseImage)
                putString("courseCategory", courseCategory)
                putString("courseName", courseName)
                putString("coursePrice", coursePrice)
                putString("courseRating", courseRating)
                putString("courseStd", courseStd)
            }
            return fragment
        }
    }
}
