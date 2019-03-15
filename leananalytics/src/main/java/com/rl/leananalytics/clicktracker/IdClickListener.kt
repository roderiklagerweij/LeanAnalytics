package com.rl.leananalytics.clicktracker

import android.util.Log
import android.view.View

/**
 * Created by Roderik on 23-05-18.
 */
class IdClickListener(private val listener : View.OnClickListener) : View.OnClickListener {

    override fun onClick(view: View) {
        listener.onClick(view)
        val viewId = view.resources.getResourceEntryName(view.id)
        Log.d("IdClickListener", "click for id: $viewId")
    }
}