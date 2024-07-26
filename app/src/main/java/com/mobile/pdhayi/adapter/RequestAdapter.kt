package com.mobile.pdhayi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.pdhayi.R
import com.mobile.pdhayi.data_modal.ConnectionDataClass
import de.hdodenhof.circleimageview.CircleImageView

class RequestAdapter(private val requests: List<ConnectionDataClass>) :
    RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val connectionImage: CircleImageView = itemView.findViewById(R.id.circleImageViewConnection)
        val nameConnection: TextView = itemView.findViewById(R.id.connectionName)
        val descriptionConnection: TextView = itemView.findViewById(R.id.descriptionConnection)
        val ignoreBtn: TextView = itemView.findViewById(R.id.ignoreConnection)
        val acceptBtn: TextView = itemView.findViewById(R.id.acceptConnection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.connection_container, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requests[position]
        holder.nameConnection.text = request.nameConnection
        holder.descriptionConnection.text = request.descriptionConnection
        Glide.with(holder.itemView.context)
            .load(request.connectionImage)
            .placeholder(R.drawable.user_normal) // Add a placeholder image in your drawable folder
            .error(R.drawable.user_normal) // Add an error image in your drawable folder
            .into(holder.connectionImage)
    }

    override fun getItemCount(): Int {
        return requests.size
    }
}
