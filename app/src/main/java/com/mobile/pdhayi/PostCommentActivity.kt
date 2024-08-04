package com.mobile.pdhayi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.bottom_sheet.comment.Comment
import com.mobile.pdhayi.bottom_sheet.comment.CommentsAdapter

class PostCommentActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_comment)

        findViewById<ImageView>(R.id.backBtnPostComment).setOnClickListener {
            finish()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.postCommentOpen)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val comments = listOf(
            Comment("Aman","https://media.licdn.com/dms/image/D4D03AQHDBktQRdQG9A/profile-displayphoto-shrink_200_200/0/1698420441044?e=2147483647&v=beta&t=Msfz2Eu7CM9GhovlxKEgfG6oP8Vsz-fDeuk3JiVduLY","I was wondering whether you had any comments about that? There's been no comment so far from police about the allegations.","1hr ago"),
            Comment("Aman","https://media.licdn.com/dms/image/D4D03AQHDBktQRdQG9A/profile-displayphoto-shrink_200_200/0/1698420441044?e=2147483647&v=beta&t=Msfz2Eu7CM9GhovlxKEgfG6oP8Vsz-fDeuk3JiVduLY","this is comment","1hr ago"),
            Comment("Aman","https://media.licdn.com/dms/image/D4D03AQHDBktQRdQG9A/profile-displayphoto-shrink_200_200/0/1698420441044?e=2147483647&v=beta&t=Msfz2Eu7CM9GhovlxKEgfG6oP8Vsz-fDeuk3JiVduLY","this is comment","1hr ago"),
            Comment("Aman","https://media.licdn.com/dms/image/D4D03AQHDBktQRdQG9A/profile-displayphoto-shrink_200_200/0/1698420441044?e=2147483647&v=beta&t=Msfz2Eu7CM9GhovlxKEgfG6oP8Vsz-fDeuk3JiVduLY","this is comment","1hr ago"),
        )
        val adapter = CommentsAdapter(comments)
        recyclerView.adapter = adapter

    }
}