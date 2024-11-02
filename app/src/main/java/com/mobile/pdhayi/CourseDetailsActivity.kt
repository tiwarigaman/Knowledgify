@file:Suppress("DEPRECATION")

package com.mobile.pdhayi

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.Util
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.pdhayi.fragment.AboutCourseFragment
import com.mobile.pdhayi.fragment.CurriculumCourseFragment

class CourseDetailsActivity : AppCompatActivity(),
    CurriculumCourseFragment.OnPlayButtonClickListener {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: StyledPlayerView
    private lateinit var thumbnail : ImageView
    private lateinit var fullScreenButton: ImageButton
    private var isFullscreen = false
    private val controlsVisibilityHandler = Handler()
    private val controlsVisibilityRunnable = Runnable {
        fullScreenButton.isVisible = false
        playerView.hideController()
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout_course)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager_course)
        thumbnail = findViewById(R.id.course_thumbnail)
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
        playerView = findViewById(R.id.player_view)
        playerView.isVisible = false
        thumbnail.isVisible = true

        //player
        playerView = findViewById(R.id.player_view)
        fullScreenButton = findViewById(R.id.full_screen_button)
        fullScreenButton.isVisible = false // Initially hidden

        fullScreenButton.setOnClickListener {
            toggleFullscreen()
        }
        playerView.setOnClickListener {
            toggleControlsVisibility()
        }
        // Initialize ExoPlayer
        initializePlayer()


//        // Prepare the MediaItem
//        val mediaItem = MediaItem.fromUri(Uri.parse("https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.webm"))
//        player.setMediaItem(mediaItem)
//
//        // Prepare and start playback
//        player.prepare()
//        player.playWhenReady = true

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

    private fun toggleFullscreen() {
        if (isFullscreen) {
            exitFullscreen()
        } else {
            enterFullscreen()
        }
    }
    private fun enterFullscreen() {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        // Hide system UI
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        // Set fullscreen mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        playerView.layoutParams.height = height
        playerView.layoutParams.width = width
        playerView.requestLayout()

        // Hide controls initially
        playerView.hideController()
        fullScreenButton.setImageResource(R.drawable.baseline_fullscreen_exit_24) // Change to exit icon
        fullScreenButton.isVisible = true
        isFullscreen = true
    }

    private fun exitFullscreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        playerView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        playerView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        playerView.requestLayout()

        playerView.showController()
        fullScreenButton.setImageResource(R.drawable.baseline_fullscreen_24) // Change to fullscreen icon
        fullScreenButton.isVisible = true
        isFullscreen = false
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                Toast.makeText(this@CourseDetailsActivity, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                Log.e("VideoPlayerActivity", "Playback error: ${error.message}")
            }
        })
    }
    private fun toggleControlsVisibility() {
        if (playerView.isControllerFullyVisible) {
            playerView.hideController()
            fullScreenButton.isVisible = false
        } else {
            playerView.showController()
            fullScreenButton.isVisible = true
            controlsVisibilityHandler.removeCallbacks(controlsVisibilityRunnable)
            controlsVisibilityHandler.postDelayed(controlsVisibilityRunnable, 3000)
        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            enterFullscreen()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            exitFullscreen()
        }
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
//    override fun onStart() {
//        super.onStart()
//        if (Util.SDK_INT > 23) {
//            player.playWhenReady = true
//        }
//    }
//

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23 || player == null) {
            player.playWhenReady = true
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            player.playWhenReady = false
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            player.playWhenReady = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onPlayButtonClick(videoUrl: String) {
        playerView.isVisible = true
        thumbnail.isVisible = false
        val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true
    }

}
