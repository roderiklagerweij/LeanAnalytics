package com.rl.leananalytics

interface TrackingAdapter {
    fun trackAction(id: String)
    fun trackPage(activityName : String)
}