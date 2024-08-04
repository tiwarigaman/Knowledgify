package com.mobile.pdhayi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.mobile.pdhayi.R
import com.mobile.pdhayi.adapter.PopularCourseAdapter
import com.mobile.pdhayi.adapter.TopMentorAdapter
import com.mobile.pdhayi.data_modal.PopularCourseDataClass
import com.mobile.pdhayi.data_modal.TopMentorDataClass

class LectureFragment : Fragment(), CategoryBottomSheet.OnCategorySelectedListener {

    private lateinit var categorySearchLecture: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lecture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categorySearchLecture = view.findViewById(R.id.categorySearchLecture)
        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider_lecture)
        val popularCoursesButton : TextView = view.findViewById(R.id.popular_see_all)
        val imageList = ArrayList<SlideModel>()
        // Set click listener on the button to replace the fragment
        popularCoursesButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PopularCoursesFragment())
                .addToBackStack(null)
                .commit()
        }

        // Add images to the list
        imageList.add(SlideModel("https://i.pinimg.com/564x/03/bc/cb/03bccb3fb8d3d8993cef1cda582f6425.jpg"))
        imageList.add(SlideModel("https://i.pinimg.com/564x/03/bc/cb/03bccb3fb8d3d8993cef1cda582f6425.jpg"))
        imageList.add(SlideModel("https://i.pinimg.com/564x/fa/43/5c/fa435c8f53e9d502476b411100345ef1.jpg"))
        imageList.add(SlideModel("https://i.pinimg.com/564x/fa/43/5c/fa435c8f53e9d502476b411100345ef1.jpg"))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
        imageSlider.startSliding(3000)
        //category
        categorySearchLecture.setOnClickListener {
            categorySearchLecture.isSelected = !categorySearchLecture.isSelected
            if (categorySearchLecture.isSelected) {
                // Open the category filter
                val bottomSheetFragment = CategoryBottomSheet()
                bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
                bottomSheetFragment.dialog?.setOnDismissListener {
                    categorySearchLecture.isSelected = false
                }
            }
        }
        //popular courses
        val recyclerView = view.findViewById<RecyclerView>(R.id.popularCourse)

        val itemList = listOf(
            PopularCourseDataClass("https://www.eustoncollege.co.uk/wp-content/uploads/2020/02/Functional-Maths-Training-Course-scaled.jpg", "Math", "Mathematics By Bikku", "499/-", "4.2", "465"),
            PopularCourseDataClass("https://media.geeksforgeeks.org/wp-content/cdn-uploads/20230706095706/intro-data-structure-%E2%80%93-1.png", "DS", "Data Structure using Python", "759", "4.1", "465"),
            PopularCourseDataClass("https://media.istockphoto.com/id/1341328946/photo/concept-of-soft-skills-mind-map-in-handwritten-style.jpg?s=612x612&w=0&k=20&c=36mLiKknI-E_4gCUWOC36eCExaqOPj8xkDpA2CQ7CL4=", "Skill", "Soft Skill", "299/-", "4.3", "465"),
            PopularCourseDataClass("https://media.istockphoto.com/id/1218970736/photo/male-and-female-students-looking-at-car-engine-on-auto-mechanic-apprenticeship-course-at.jpg?s=612x612&w=0&k=20&c=kuofsjqvmyL9_w11-VBXRpf_G7dMogVR-k2Hpu-wNqE=", "Mechanics", "Workshop", "699/-", "4.2", "465"),
            PopularCourseDataClass("https://www.shutterstock.com/image-illustration/c-code-on-dark-background-600nw-1896170293.jpg", "Coding", "Cpp Basics", "599", "4.3", "465"),
            // Add more items
        )

        // Use the layout you need for this fragment
        val adapter = PopularCourseAdapter(itemList, R.layout.course_container)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        //top mentor
        val recyclerViewTopMentor = view.findViewById<RecyclerView>(R.id.topMentor)
        val itemListTopMentor = listOf(
            TopMentorDataClass("https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Aman"),
            TopMentorDataClass("https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Tiwari ji"),
            TopMentorDataClass("https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Gamora"),
            TopMentorDataClass("https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Dinesh"),
            TopMentorDataClass("https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Raju"),
            TopMentorDataClass("https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Rencho")
        )
        recyclerViewTopMentor.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTopMentor.adapter = TopMentorAdapter(itemListTopMentor)

    }

    override fun onCategorySelected(category: String) {
        //nothing implemented
    }
}