package com.mobile.pdhayi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.TopMentorDataClass

class TopMentorAdapter(private val itemList: List<TopMentorDataClass>) : RecyclerView.Adapter<TopMentorAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mentorImage: ShapeableImageView = itemView.findViewById(R.id.top_mentor_image)
        val mentorName : TextView = itemView.findViewById(R.id.top_mentor_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_mentor_container, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.mentorImage)
        holder.mentorName.text = currentItem.name

    }

    override fun getItemCount(): Int = itemList.size
}