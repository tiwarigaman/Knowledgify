package com.mobile.pdhayi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.GroupsMessageDataClass
import de.hdodenhof.circleimageview.CircleImageView

class GroupsAdapter(private val group: List<GroupsMessageDataClass>) :
    RecyclerView.Adapter<GroupsAdapter.GroupViewHolder>() {

    class GroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groupImage : CircleImageView = view.findViewById(R.id.group_image_chat)
        val groupName : TextView = view.findViewById(R.id.group_name_chat)
        val followers : TextView = view.findViewById(R.id.group_follower_chat)
        val followerButton : TextView = view.findViewById(R.id.group_follow_button_chat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group_message, parent, false)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val groupItems = group[position]
        holder.groupName.text = groupItems.groupName
        holder.followers.text = groupItems.groupFollower
        Glide.with(holder.itemView.context).load(groupItems.image).into(holder.groupImage)
    }

    override fun getItemCount(): Int = group.size
}