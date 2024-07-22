package com.mobile.pdhayi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.StoryDataClass
import de.hdodenhof.circleimageview.CircleImageView

class StoryAdapter(private val itemList: List<StoryDataClass>) : RecyclerView.Adapter<StoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: CircleImageView = itemView.findViewById(R.id.imageStory)
        val itemBody: TextView = itemView.findViewById(R.id.storyName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.story_container, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.itemImage)
        holder.itemBody.text = currentItem.name
    }

    override fun getItemCount(): Int = itemList.size
}