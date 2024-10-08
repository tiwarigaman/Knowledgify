package com.mobile.pdhayi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.mobile.pdhayi.CourseDetailsActivity
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.PopularCourseDataClass

class PopularCourseAdapter(
    private val itemList: List<PopularCourseDataClass>,
    private val layoutResId: Int
) : RecyclerView.Adapter<PopularCourseAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseImage: ShapeableImageView = itemView.findViewById(R.id.popular_course_image)
        val courseCategory : TextView = itemView.findViewById(R.id.popular_course_category)
        val courseName : TextView = itemView.findViewById(R.id.popular_course_name)
        val coursePrice : TextView = itemView.findViewById(R.id.popular_course_price)
        val courseRating : TextView = itemView.findViewById(R.id.popular_course_rating)
        val courseStd : TextView = itemView.findViewById(R.id.popular_course_registered_student)
        val courseFavorite : ImageView = itemView.findViewById(R.id.popular_course_bookmark)
        val click : LinearLayout = itemView.findViewById(R.id.course_click)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        Glide.with(holder.itemView.context).load(currentItem.courseImage).into(holder.courseImage)
        holder.courseCategory.text = currentItem.courseCategory
        holder.courseName.text = currentItem.courseName
        holder.coursePrice.text = currentItem.coursePrice
        holder.courseRating.text = currentItem.courseRating
        holder.courseStd.text = currentItem.courseStd
        holder.click.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CourseDetailsActivity::class.java).apply {
                putExtra("courseImage", currentItem.courseImage)
                putExtra("courseCategory", currentItem.courseCategory)
                putExtra("courseName", currentItem.courseName)
                putExtra("coursePrice", currentItem.coursePrice)
                putExtra("courseRating", currentItem.courseRating)
                putExtra("courseStd", currentItem.courseStd)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = itemList.size
}
