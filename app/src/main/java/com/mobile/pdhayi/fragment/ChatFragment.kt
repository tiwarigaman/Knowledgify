package com.mobile.pdhayi.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.R
import com.mobile.pdhayi.adapter.ChatAdapter
import com.mobile.pdhayi.adapter.GroupsAdapter
import com.mobile.pdhayi.data_modal.ChatMessageDataClass
import com.mobile.pdhayi.data_modal.GroupsMessageDataClass

class ChatFragment : Fragment() {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var chatMessages: MutableList<ChatMessageDataClass>
    private lateinit var groupRecyclerView: RecyclerView
    private lateinit var groupAdapter: GroupsAdapter
    private lateinit var group: MutableList<GroupsMessageDataClass>
    private var callback: OnBackButtonClickListener? = null

    interface OnBackButtonClickListener {
        fun onBackButtonClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBackButtonClickListener) {
            callback = context
        } else {
            throw RuntimeException("$context must implement OnBackButtonClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backButton: ImageView = view.findViewById(R.id.backBtnChatView)
        backButton.setOnClickListener {
            callback?.onBackButtonClicked()
        }

        // Add any other initialization or setup code here
//        val chatRecycler = view.findViewById<RecyclerView>(R.id.chats_view_recycler_view)
        chatMessages = mutableListOf(
            ChatMessageDataClass("JohnDoe", "Hey, how are you?", "2m ago"),
            ChatMessageDataClass("JaneDoe", "I'm good, thanks!", "1m ago"),
            ChatMessageDataClass("JaneDoe", "I'm good, thanks!", "1m ago"),
            ChatMessageDataClass("JaneDoe", "I'm good, thanks!", "1m ago"),
            ChatMessageDataClass("JaneDoe", "I'm good, thanks!", "1m ago"),
            ChatMessageDataClass("JaneDoe", "I'm good, thanks!", "1m ago"),
            // Add more messages here
        )

        chatRecyclerView = view.findViewById(R.id.chats_view_recycler_view)
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        chatAdapter = ChatAdapter(chatMessages)
        chatRecyclerView.adapter = chatAdapter

        group = mutableListOf(
            GroupsMessageDataClass("https://media.licdn.com/dms/image/D4D0BAQFrakRGrCgo1g/company-logo_200_200/0/1694266771631/aarambhcommunity_logo?e=2147483647&v=beta&t=bjy0Br5i7fcbXsM5TWMtB2vR1WDm4YExezs4uwMY-rU", "Aarambh", "376k followers"),
            GroupsMessageDataClass("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCyRyjptPHq17q2JDbHz_4N1mPGGjz44FAew&s", "GDSC", "985k followers"),
            // Add more messages here
        )
        groupRecyclerView = view.findViewById(R.id.groups_view_recycler_view)
        groupRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        groupAdapter = GroupsAdapter(group)
        groupRecyclerView.adapter = groupAdapter
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }
}
