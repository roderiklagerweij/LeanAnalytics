package com.rl.leananalytics.clicktracker

import android.view.View
import com.rl.leananalytics.TrackingAdapter

class IdClickListener(private val listener : View.OnClickListener, val trackingAdapter: TrackingAdapter) : View.OnClickListener {

    override fun onClick(view: View) {
        listener.onClick(view)
        val viewId = view.resources.getResourceEntryName(view.id)
        trackingAdapter.trackAction(viewId)
    }
}