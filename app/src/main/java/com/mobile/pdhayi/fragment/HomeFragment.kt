package com.mobile.pdhayi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.R
import com.mobile.pdhayi.adapter.PostAdapter
import com.mobile.pdhayi.adapter.StoryAdapter
import com.mobile.pdhayi.data_modal.PostDataClass
import com.mobile.pdhayi.data_modal.StoryDataClass

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewPost)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val dataList = listOf(
            PostDataClass("https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face.png",
                "Item 1","https://upload.wikimedia.org/wikipedia/commons/7/74/Posts_on_the_saltmarsh%2C_Warton_Sands_-_geograph.org.uk_-_1658558.jpg","Hey i'm posting this post in my profile","10","100","Aj ki"),
            PostDataClass("https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face.png",
                "Item 1","https://upload.wikimedia.org/wikipedia/commons/7/74/Posts_on_the_saltmarsh%2C_Warton_Sands_-_geograph.org.uk_-_1658558.jpg","Hey i'm posting this post in my profile","10","100","Aj ki"),
            PostDataClass("https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face.png",
                "Item 1","https://upload.wikimedia.org/wikipedia/commons/7/74/Posts_on_the_saltmarsh%2C_Warton_Sands_-_geograph.org.uk_-_1658558.jpg","Hey i'm posting this post in my profile","10","100","Aj ki"),
            PostDataClass("https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face.png",
                "Item 1","https://upload.wikimedia.org/wikipedia/commons/7/74/Posts_on_the_saltmarsh%2C_Warton_Sands_-_geograph.org.uk_-_1658558.jpg","Hey i'm posting this post in my profile","10","100","Aj ki"),
        )

        val adapter = PostAdapter(dataList)
        recyclerView.adapter = adapter

        val recyclerView2: RecyclerView = view.findViewById(R.id.recyclerViewStory)
        recyclerView2.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        val dataList2 = listOf(
            StoryDataClass("https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face.png", "Item 1"),
            StoryDataClass("https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face.png", "Item 1"),
            StoryDataClass("https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face.png", "Item 1"),
            StoryDataClass("https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face.png", "Item 1"),
            StoryDataClass("https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face.png", "Item 1"),
            StoryDataClass("https://e7.pngegg.com/pngimages/698/39/png-clipart-computer-icons-user-profile-info-miscellaneous-face.png", "Item 2"),
            StoryDataClass("https://e7.pngegg.com/pngimages/698/39/png-clipart-computer-icons-user-profile-info-miscellaneous-face.png", "Item 2"),
            StoryDataClass("https://e7.pngegg.com/pngimages/698/39/png-clipart-computer-icons-user-profile-info-miscellaneous-face.png", "Item 2"),
            StoryDataClass("https://e7.pngegg.com/pngimages/698/39/png-clipart-computer-icons-user-profile-info-miscellaneous-face.png", "Item 2"),
            StoryDataClass("https://e7.pngegg.com/pngimages/698/39/png-clipart-computer-icons-user-profile-info-miscellaneous-face.png", "Item 2"),
            StoryDataClass("https://e7.pngegg.com/pngimages/698/39/png-clipart-computer-icons-user-profile-info-miscellaneous-face.png", "Item 2"),
            StoryDataClass("https://e7.pngegg.com/pngimages/698/39/png-clipart-computer-icons-user-profile-info-miscellaneous-face.png", "Item 2"),
            StoryDataClass("https://e7.pngegg.com/pngimages/698/39/png-clipart-computer-icons-user-profile-info-miscellaneous-face.png", "Item 2"),
            // Add more items
        )

        val adapter2 = StoryAdapter(dataList2)
        recyclerView2.adapter = adapter2
    }
}