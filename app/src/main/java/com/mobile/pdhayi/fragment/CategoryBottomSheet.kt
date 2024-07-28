package com.mobile.pdhayi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.pdhayi.R

class CategoryBottomSheet : BottomSheetDialogFragment() {

    interface OnCategorySelectedListener {
        fun onCategorySelected(category: String)
    }

    var listener: OnCategorySelectedListener? = null

    private val categories = listOf(
        "Machine Learning", "Graphic Design", "Data Science", "Web Development",
        "Mobile Development", "Cyber Security", "Artificial Intelligence"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_categories)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CategoryAdapter(categories) { category ->
            listener?.onCategorySelected(category)
            dismiss()
        }
    }

    class CategoryAdapter(
        private val categories: List<String>,
        private val itemClick: (String) -> Unit
    ) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
            return CategoryViewHolder(view)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            holder.bind(categories[position])
        }

        override fun getItemCount(): Int = categories.size

        inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(category: String) {
                itemView.findViewById<TextView>(android.R.id.text1).text = category
                itemView.setOnClickListener { itemClick(category) }
            }
        }
    }
}
