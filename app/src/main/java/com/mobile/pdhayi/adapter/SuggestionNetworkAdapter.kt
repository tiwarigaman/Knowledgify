package com.mobile.pdhayi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.SuggestionNetworkDataClass
import de.hdodenhof.circleimageview.CircleImageView

class SuggestionNetworkAdapter(private val suggestions: List<SuggestionNetworkDataClass>) :
    RecyclerView.Adapter<SuggestionNetworkAdapter.SuggestionViewHolder>() {

    class SuggestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val suggestionImage1: CircleImageView = itemView.findViewById(R.id.circleImageViewNetworkAdd)
        val nameSuggestion: TextView = itemView.findViewById(R.id.networkAddName)
        val descriptionSuggestion: TextView = itemView.findViewById(R.id.descriptionNetworkAdd)
        val connectButton: TextView = itemView.findViewById(R.id.connectBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.network_add_container, parent, false)
        return SuggestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
        val suggestion = suggestions[position]
        holder.nameSuggestion.text = suggestion.nameSuggestion
        holder.descriptionSuggestion.text = suggestion.descriptionSuggestion
        Glide.with(holder.itemView.context)
            .load(suggestion.suggestionImage)
            .placeholder(R.drawable.user_normal) // Add a placeholder image in your drawable folder
            .error(R.drawable.user_normal) // Add an error image in your drawable folder
            .into(holder.suggestionImage1)
    }

    override fun getItemCount(): Int {
        return suggestions.size
    }
}
