package com.mobile.pdhayi.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.NotificationDataClass

class NotificationAdapter(private val notifications: List<NotificationDataClass>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private var selectedItem: Int = RecyclerView.NO_POSITION

    class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.notification_username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_container, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        val fullMessage = "${notification.title} ${notification.content} ${notification.timestamp}"
        val spannableString = SpannableString(fullMessage)
        val titleColor = ContextCompat.getColor(holder.itemView.context, R.color.lite_white)
        // Bold and color the title
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            notification.title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(Color.BLUE), // Change this to your desired color
            0,
            notification.title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Reduce the size of the content
        val contentStart = fullMessage.indexOf(notification.content)
        val contentEnd = contentStart + notification.content.length
        spannableString.setSpan(
            RelativeSizeSpan(0.9f), // Reduce size to 90% of the default
            contentStart,
            contentEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Reduce the size of the timestamp
        val timestampStart = fullMessage.indexOf(notification.timestamp)
        spannableString.setSpan(
            RelativeSizeSpan(0.8f), // Reduce size to 80% of the default
            timestampStart,
            fullMessage.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(titleColor), // Change this to your desired color
            0,
            notification.title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        holder.titleTextView.text = spannableString

        holder.itemView.isSelected = selectedItem == position
        holder.itemView.setOnClickListener {
            notifyItemChanged(selectedItem)
            selectedItem = holder.adapterPosition
            notifyItemChanged(selectedItem)
        }
    }

    override fun getItemCount() = notifications.size
}
