package com.mobile.pdhayi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.R
import com.mobile.pdhayi.adapter.PopularCourseAdapter
import com.mobile.pdhayi.data_modal.PopularCourseDataClass

class PopularCoursesFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var backBtnPopularCourse: ImageView
    private lateinit var titlePopularCourse: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backBtnPopularCourse = view.findViewById(R.id.backBtnPopularCourse)
        titlePopularCourse = view.findViewById(R.id.popular_course_title)
        searchView = view.findViewById(R.id.searchViewPopularCourses12)

        backBtnPopularCourse.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        searchView.setOnClickListener {
            resizeSearchView(ViewGroup.LayoutParams.MATCH_PARENT)
            hideElements()
        }

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                resizeSearchView(ViewGroup.LayoutParams.MATCH_PARENT)
                hideElements()
                searchView.setBackgroundResource(R.drawable.seacrh_bar)
            } else {
                resizeSearchView(ViewGroup.LayoutParams.WRAP_CONTENT)
                showElements()
                searchView.clearFocus()
                searchView.setBackgroundResource(0)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("PopularCoursesFragment", "onQueryTextSubmit called")
                if (searchView.hasFocus()) performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optionally handle text changes here
                return true
            }
        })

        //popular course preview...
        val recyclerView = view.findViewById<RecyclerView>(R.id.popular_course_recycler_view)

        val itemList = listOf(
            PopularCourseDataClass("https://www.eustoncollege.co.uk/wp-content/uploads/2020/02/Functional-Maths-Training-Course-scaled.jpg", "Math", "Mathematics By Bikku", "499/-", "4.2", "465"),
            PopularCourseDataClass("https://media.geeksforgeeks.org/wp-content/cdn-uploads/20230706095706/intro-data-structure-%E2%80%93-1.png", "DS", "Data Structure using Python", "759", "4.1", "465"),
            PopularCourseDataClass("https://media.istockphoto.com/id/1341328946/photo/concept-of-soft-skills-mind-map-in-handwritten-style.jpg?s=612x612&w=0&k=20&c=36mLiKknI-E_4gCUWOC36eCExaqOPj8xkDpA2CQ7CL4=", "Skill", "Soft Skill", "299/-", "4.3", "465"),
            PopularCourseDataClass("https://media.istockphoto.com/id/1218970736/photo/male-and-female-students-looking-at-car-engine-on-auto-mechanic-apprenticeship-course-at.jpg?s=612x612&w=0&k=20&c=kuofsjqvmyL9_w11-VBXRpf_G7dMogVR-k2Hpu-wNqE=", "Mechanics", "Workshop", "699/-", "4.2", "465"),
            PopularCourseDataClass("https://www.shutterstock.com/image-illustration/c-code-on-dark-background-600nw-1896170293.jpg", "Coding", "Cpp Basics", "599", "4.3", "465"),
            // Add more items
        )

        // Use the layout you need for this fragment
        val adapter = PopularCourseAdapter(itemList, R.layout.course_container_full)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun resizeSearchView(width: Int) {
        searchView.layoutParams = searchView.layoutParams.apply { this.width = width }
    }

    private fun performSearch(query: String?) {
        query?.let {
            Log.d("PopularCoursesFragment", "Search query: $it")
            Toast.makeText(requireContext(), "Search query: $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hideElements() {
        backBtnPopularCourse.visibility = View.GONE
        titlePopularCourse.visibility = View.GONE
    }

    private fun showElements() {
        backBtnPopularCourse.visibility = View.VISIBLE
        titlePopularCourse.visibility = View.VISIBLE
    }
}
