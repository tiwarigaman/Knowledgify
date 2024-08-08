package com.mobile.pdhayi.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.R
import com.mobile.pdhayi.adapter.CurriculumAdapter
import com.mobile.pdhayi.data_modal.CurriculumDataClass

class CurriculumCourseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curriculum_course, container, false)
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
        val priceView : TextView = view.findViewById(R.id.enroll_course_price2)
        priceView.text = "Enroll Course - $coursePrice"

        val recyclerView = view.findViewById<RecyclerView>(R.id.curriculumRecyclerView)
        //prepare list
        val itemList  = listOf(
            CurriculumDataClass("1","Get the most from this!","15 min","//","https://fms.aktu.ac.in/Resources/aktu/pdf/syllabus/Syllabus2324/B.Tech_2nd_Yr_CSE_v3.pdf"),
            CurriculumDataClass("2","Thanks a lot for taking the course","7 min","//","https://fms.aktu.ac.in/Resources/aktu/pdf/syllabus/Syllabus2324/B.Tech_2nd_Yr_CSE_v3.pdf"),
            CurriculumDataClass("3","How to get most out of this course","9 min","//","https://fms.aktu.ac.in/Resources/aktu/pdf/syllabus/Syllabus2324/B.Tech_2nd_Yr_CSE_v3.pdf"),
            CurriculumDataClass("4","Kotlin Updates and Why you should..","20 min","//","https://fms.aktu.ac.in/Resources/aktu/pdf/syllabus/Syllabus2324/B.Tech_2nd_Yr_CSE_v3.pdf"),
        )

        val adapter = CurriculumAdapter(itemList, R.layout.item_view_curriculum)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

    }
}