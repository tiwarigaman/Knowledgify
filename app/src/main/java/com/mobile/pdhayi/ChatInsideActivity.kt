package com.mobile.pdhayi

import android.os.Bundle
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.adapter.ChatInsideAdapter
import com.mobile.pdhayi.data_modal.ChatMessageDataClass

class ChatInsideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_inside)
        val senderName = intent.getStringExtra("senderName")
        val messageContent = intent.getStringExtra("messageContent")
        val timestamp = intent.getStringExtra("timestamp")
        if (senderName != null && messageContent != null && timestamp != null) {
            findViewById<TextView>(R.id.username_chat_inside).text = senderName
        }
        // For demonstration purposes, set current user
        val currentUser = senderName // Replace with actual current user's name

        val chatMessages = mutableListOf(
            ChatMessageDataClass(senderName ?: "", messageContent ?: "", timestamp ?: ""),
            // Add more messages here
        )

        val chatRecyclerView: RecyclerView = findViewById(R.id.chat_preview_recycler_view)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        val chatAdapter = ChatInsideAdapter(chatMessages, "currentUser")
        chatRecyclerView.adapter = chatAdapter
        //send button

        val sendButton: FrameLayout = findViewById(R.id.sendButton)
        val messageInput: EditText = findViewById(R.id.messageUser) // Ensure you have an EditText for input

        sendButton.setOnClickListener {
            val newMessageContent = messageInput.text.toString()
            if (newMessageContent.isNotEmpty()) {
                // Create new message and update the list
                val newMessage = ChatMessageDataClass("currentUser", newMessageContent, "just now")
                val newMessage2 = ChatMessageDataClass("anomy", "hehe", "just now")
                chatMessages.add(newMessage)
                chatMessages.add(newMessage2)

                // Notify the adapter and scroll to the bottom
                chatAdapter.notifyItemInserted(chatMessages.size - 1)
                chatRecyclerView.scrollToPosition(chatMessages.size - 1)

                // Clear the input field
                messageInput.text.clear()
            }
        }
    }
}