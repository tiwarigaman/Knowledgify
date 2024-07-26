package com.mobile.pdhayi.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.R
import com.mobile.pdhayi.adapter.RequestAdapter
import com.mobile.pdhayi.adapter.SuggestionNetworkAdapter
import com.mobile.pdhayi.data_modal.ConnectionDataClass
import com.mobile.pdhayi.data_modal.SuggestionNetworkDataClass

class SearchFragment : Fragment() {
    private lateinit var recyclerViewConnection: RecyclerView
    private lateinit var recyclerViewNetworkAdd: RecyclerView
    private lateinit var showMoreTextView: TextView
    private val requests = mutableListOf<ConnectionDataClass>()
    private val requestsNetwork = mutableListOf<SuggestionNetworkDataClass>()
    private val maxVisibleRequests = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewConnection = view.findViewById(R.id.recyclerViewConnection)
        recyclerViewNetworkAdd = view.findViewById(R.id.recyclerViewNetworkAdd)
        showMoreTextView = view.findViewById(R.id.show_more_connection)

        val invitationCount : TextView = view.findViewById(R.id.invitation_count)
        invitationCount.text = "Invitations (6)"

        // Simulating adding requests
        requests.add(ConnectionDataClass("https://images.indianexpress.com/2018/04/avengers-captain-america.jpg?w=350", "Captain America", "Avengers who saves the world"))
        requests.add(ConnectionDataClass("https://images.indianexpress.com/2018/04/avengers-iron-man.jpg", "Iron Man", "Gave his life for save the earth"))
        requests.add(ConnectionDataClass("https://pbs.twimg.com/profile_images/1607576872589889537/mQxBneCJ_400x400.jpg", "Xavier", "Ayien ka kaheo"))
        requests.add(ConnectionDataClass("https://pbs.twimg.com/profile_images/1360181645580537860/h7vh3gGP_400x400.jpg", "John", "John bnega don. mujhe rokega kon"))
        requests.add(ConnectionDataClass("https://i.pinimg.com/originals/56/31/22/56312264db7ff9c2cf7b11a6eccb4633.jpg", "Shin-chan", "So I thought if I put the toaster in the freezer..."))
        requests.add(ConnectionDataClass("https://humenglish.com/wp-content/uploads/2024/07/Untitled-114.jpg", "Mr. Bean", "British sitcom created by Rowan Atkinson and Richard Curtis..."))
        // adding network
        for (i in 1..10) {
            requestsNetwork.add(SuggestionNetworkDataClass("https://humenglish.com/wp-content/uploads/2024/07/Untitled-11$i.jpg", "Name $i", "Description $i"))
        }

        updateRecyclerView()
        updateRecyclerViewForNetworkAdd()

        // Show More button click listener
        showMoreTextView.setOnClickListener {
            val adapter = RequestAdapter(requests)
            recyclerViewConnection.adapter = adapter
            showMoreTextView.visibility = View.GONE
        }

    }

    private fun updateRecyclerViewForNetworkAdd() {
        val adapter = SuggestionNetworkAdapter(requestsNetwork)
        recyclerViewNetworkAdd.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNetworkAdd.adapter = adapter
    }

    private fun updateRecyclerView() {
        val visibleRequests = if (requests.size > maxVisibleRequests) {
            showMoreTextView.visibility = View.VISIBLE
            requests.take(maxVisibleRequests)
        } else {
            showMoreTextView.visibility = View.GONE
            requests
        }

        val adapter = RequestAdapter(visibleRequests)
        recyclerViewConnection.layoutManager = LinearLayoutManager(context)
        recyclerViewConnection.adapter = adapter
    }
}