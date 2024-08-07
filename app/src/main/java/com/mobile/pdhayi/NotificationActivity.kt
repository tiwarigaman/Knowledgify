package com.mobile.pdhayi

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.adapter.NotificationAdapter
import com.mobile.pdhayi.data_modal.NotificationDataClass

class NotificationActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var notifications: List<NotificationDataClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        findViewById<ImageView>(R.id.backBtnNotification).setOnClickListener {
            finish()
        }
        notifications = listOf(
            NotificationDataClass("tiwarig_aman", "Commented on your post", "just now"),
            NotificationDataClass("mishra_upma420", "Commented on your post( hamare yha pool placement hota tha)", "30 min ago"),
            NotificationDataClass("aapka_dost", "Commented on your post(Daru piyega bhai?)", "10 days ago")
        )

        recyclerView = findViewById(R.id.notificationRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        notificationAdapter = NotificationAdapter(notifications)
        recyclerView.adapter = notificationAdapter

    }
}