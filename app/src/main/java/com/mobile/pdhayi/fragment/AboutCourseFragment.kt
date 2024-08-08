package com.mobile.pdhayi.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mobile.pdhayi.R

class AboutCourseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_course, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val courseImage = arguments?.getString("courseImage")
        val courseCategory = arguments?.getString("courseCategory")
        val courseName = arguments?.getString("courseName")
        val coursePrice = arguments?.getString("coursePrice")
        val courseRating = arguments?.getString("courseRating")
        val courseStd = arguments?.getString("courseStd")

        val priceView : TextView = view.findViewById(R.id.enroll_course_price)
        priceView.text = "Enroll Course - $coursePrice"

    }

}