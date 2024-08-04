package com.mobile.pdhayi.bottom_sheet.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.pdhayi.R
import de.hdodenhof.circleimageview.CircleImageView

class CommentsAdapter(private val comments: List<Comment>) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int = comments.size

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.textViewUserName)
        private val commentTextView: TextView = itemView.findViewById(R.id.textViewComment)
        private val commentTimeTextView: TextView = itemView.findViewById(R.id.textViewCommentTime)
        private val userProfileImageView: CircleImageView = itemView.findViewById(R.id.imageViewUserProfile)

        fun bind(comment: Comment) {
            userNameTextView.text = comment.userName
            commentTextView.text = comment.commentText
            commentTimeTextView.text = comment.commentTime
            Glide.with(itemView.context).load(comment.userProfileUrl).into(userProfileImageView)
        }
    }
}
