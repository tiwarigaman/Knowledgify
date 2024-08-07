package com.mobile.pdhayi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.ChatInsideActivity
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.ChatMessageDataClass

class ChatAdapter(private val chatMessages: List<ChatMessageDataClass>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val senderNameTextView: TextView = view.findViewById(R.id.senderNameTextView)
        val messageContentTextView: TextView = view.findViewById(R.id.messageContentTextView)
        val timestampTextView: TextView = view.findViewById(R.id.timestampTextView)
        val select : LinearLayout = view.findViewById(R.id.chat_select)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatMessage = chatMessages[position]
        holder.senderNameTextView.text = chatMessage.senderName
        holder.messageContentTextView.text = chatMessage.messageContent
        holder.timestampTextView.text = chatMessage.timestamp
        holder.select.setOnClickListener {
            //open chat activity
            val intent = Intent(holder.itemView.context, ChatInsideActivity::class.java).apply {
                putExtra("senderName", chatMessage.senderName)
                putExtra("messageContent", chatMessage.messageContent)
                putExtra("timestamp", chatMessage.timestamp)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = chatMessages.size
}