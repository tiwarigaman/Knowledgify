package com.mobile.pdhayi.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.pdhayi.PDFViewerActivity
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.CurriculumDataClass

class CurriculumAdapter(
    private val itemList: List<CurriculumDataClass>,
    private val layoutResId: Int
) : RecyclerView.Adapter<CurriculumAdapter.MyViewHolder>() {
    private var onPlayButtonClickListener: ((String) -> Unit)? = null
    var playingItemPosition: Int = -1  // Track the position of the currently playing item
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val courseImage: ShapeableImageView = itemView.findViewById(R.id.popular_course_image)
        val number : TextView = itemView.findViewById(R.id.course_number)
        val title : TextView = itemView.findViewById(R.id.course_chapter_title)
        val duration : TextView = itemView.findViewById(R.id.course_chapter_duration)
        val play : ImageView = itemView.findViewById(R.id.course_play)
        val pdf : ImageView = itemView.findViewById(R.id.view_notes)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
//        Glide.with(holder.itemView.context).load(currentItem.courseImage).into(holder.courseImage)

        holder.number.text = "0${currentItem.sequence}"
        holder.title.text = currentItem.name
        holder.duration.text = currentItem.duration
        // Update the play button icon based on whether this item is playing
        if (position == playingItemPosition) {
            holder.play.setImageResource(R.drawable.baseline_play_lesson_24) // Replace with your pause icon
        } else {
            holder.play.setImageResource(R.drawable.baseline_play_circle_filled_24) // Replace with your play icon
        }

        holder.pdf.setOnClickListener {
            val intent = Intent(holder.itemView.context, PDFViewerActivity::class.java)
            intent.putExtra("PDF_URL", currentItem.pdfUrl)
            intent.putExtra("PDF_TITLE", "Sample PDF")
            holder.itemView.context.startActivity(intent)
        }
        holder.play.setOnClickListener {
//            onPlayButtonClickListener?.invoke(currentItem.video)
            // Notify the listener and update the play button state
            onPlayButtonClickListener?.invoke(currentItem.video)
        }

    }

    override fun getItemCount(): Int = itemList.size
    fun setOnPlayButtonClickListener(listener: (String) -> Unit) {
        onPlayButtonClickListener = listener
    }
}
