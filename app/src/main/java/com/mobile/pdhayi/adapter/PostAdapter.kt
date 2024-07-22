package com.mobile.pdhayi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.PostDataClass

class PostAdapter(private val itemList: List<PostDataClass>) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemHead: TextView = itemView.findViewById(R.id.item_head_post)
        val itemBody: TextView = itemView.findViewById(R.id.item_body_post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_container, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemHead.text = currentItem.itemHead
        holder.itemBody.text = currentItem.itemBody
    }

    override fun getItemCount(): Int = itemList.size
}
