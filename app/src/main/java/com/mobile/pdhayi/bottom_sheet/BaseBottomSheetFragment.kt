package com.mobile.pdhayi.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.pdhayi.R

class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    private var layoutResId: Int = 0
    private var adapter: RecyclerView.Adapter<*>? = null

    companion object {
        private const val ARG_LAYOUT_RES_ID = "layout_res_id"
        private const val ARG_ADAPTER = "adapter"

        fun newInstance(layoutResId: Int, adapter: RecyclerView.Adapter<*>): BaseBottomSheetFragment {
            val fragment = BaseBottomSheetFragment()
            val args = Bundle()
            args.putInt(ARG_LAYOUT_RES_ID, layoutResId)
            fragment.arguments = args
            fragment.adapter = adapter
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            layoutResId = it.getInt(ARG_LAYOUT_RES_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutResId, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewForBottomSheet)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        return view
    }
}
