package com.mobile.pdhayi

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: OnboardingAdapter
    private lateinit var onboardingItems: List<OnboardingItem>
    private lateinit var buttonSkip: TextView
    private lateinit var buttonNext: Button
    private lateinit var indicatorContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        viewPager = findViewById(R.id.viewPager)
        buttonSkip = findViewById(R.id.buttonSkip)
        buttonNext = findViewById(R.id.buttonNext)
        indicatorContainer = findViewById(R.id.indicatorContainer)


        onboardingItems = listOf(
            OnboardingItem("Learn with Fun: Interactive Lessons for All Ages", "Engage in interactive, fun lessons across various subjects",R.raw.welcome_onboarding),
            OnboardingItem("Math Mastery: Your Path to Numerical Excellence", "Achieve numerical excellence with engaging math tutorials",R.raw.expert_led_lecture_onboarding),
            OnboardingItem("Explorer: Discover the Wonders of Science", "Dive into science with interactive experiments and lessons",R.raw.interactive_learning_onboarding),
            OnboardingItem( "Master New Languages with Ease", "Learn new languages through immersive, fun exercises",R.raw.community_onboarding),
            OnboardingItem("History Hub: Uncover the Past, Understand the Future", "Explore history through engaging and interactive lessons",R.raw.mentor_onboarding)
        )

        adapter = OnboardingAdapter(onboardingItems)
        viewPager.adapter = adapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (viewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        buttonSkip.setOnClickListener {
            // Handle Skip button click
            // Navigate to the main activity or close the onboarding screen
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        buttonNext.setOnClickListener {
            if (viewPager.currentItem < onboardingItems.size - 1) {
                viewPager.currentItem += 1
            } else {
                // Handle last page, navigate to main activity or close the onboarding screen
            }
        }

        setIndicators()
        setCurrentIndicator(0)
    }

    private fun setIndicators() {
        val indicators = arrayOfNulls<ImageView>(adapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(pos: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imgView = indicatorContainer.getChildAt(i) as ImageView
            if (i == pos) {
                imgView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_bg
                    )
                )
            } else {
                imgView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
            }
        }
    }
}
