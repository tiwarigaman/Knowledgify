package com.mobile.pdhayi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.mobile.pdhayi.R

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

        val imageList = ArrayList<SlideModel>()

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
    }

    override fun onCategorySelected(category: String) {
        //nothing implemented
    }
}