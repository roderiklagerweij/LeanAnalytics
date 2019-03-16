package com.rl.leananalytics

interface TrackingAdapter {
    fun trackAction(id: String)
    fun trackActivity(activityName : String)
}