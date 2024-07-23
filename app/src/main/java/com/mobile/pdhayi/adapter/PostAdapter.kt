package com.mobile.pdhayi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.PostDataClass
import de.hdodenhof.circleimageview.CircleImageView

class PostAdapter(private val itemList: List<PostDataClass>) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postPersonImage: CircleImageView = itemView.findViewById(R.id.personImagePost)
        val namePerson: TextView = itemView.findViewById(R.id.name_post_person)
        val descPerson: TextView = itemView.findViewById(R.id.desc_post)
        val postImage: ImageView = itemView.findViewById(R.id.imagePost)
        val bulbIndicator : ImageView = itemView.findViewById(R.id.bulb_post)
        val commentPost : ImageView = itemView.findViewById(R.id.comment_post)
        val likes : TextView = itemView.findViewById(R.id.like_post)
        val date : TextView = itemView.findViewById(R.id.date_post)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_container, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        Glide.with(holder.itemView.context).load(currentItem.imageUser).into(holder.postPersonImage)
        holder.namePerson.text = currentItem.nameUser
        Glide.with(holder.itemView.context).load(currentItem.imagePost).into(holder.postImage)
        holder.descPerson.text = currentItem.descUser
        holder.likes.text = "${currentItem.likeUser} Likes"
        holder.date.text = currentItem.dateUser

        var a = 0
        holder.bulbIndicator.setOnClickListener {
            if(a%2==0){
                holder.bulbIndicator.setImageResource(R.drawable.lightbulb_on)
            }else{
                holder.bulbIndicator.setImageResource(R.drawable.lightbulb_off)
            }
            a++
        }
    }

    override fun getItemCount(): Int = itemList.size
}
