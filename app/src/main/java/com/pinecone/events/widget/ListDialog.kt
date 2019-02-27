package com.pinecone.events.widget

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.pinecone.events.R
import com.pinecone.events.ui.newEvent.PlaceAdapter

class ListDialog : DialogFragment() {
    private lateinit var activity: Context
    private lateinit var adapter: PlaceAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var btCancel: Button

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.dialog_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        btCancel = view.findViewById(R.id.btCancel)
        btCancel.setOnClickListener { dismiss() }
        return view
    }

    companion object {
        fun newInstance(context: Context, adapter: PlaceAdapter): ListDialog {
            val dialogFragment = ListDialog()
            dialogFragment.activity = context
            dialogFragment.adapter = adapter
            return dialogFragment
        }
    }

    override fun onResume() {
        super.onResume()
        val params = dialog.window!!.attributes
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = params as android.view.WindowManager.LayoutParams
    }

}