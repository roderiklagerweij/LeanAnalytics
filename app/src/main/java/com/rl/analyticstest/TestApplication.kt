package com.rl.analyticstest

import android.app.Application
import com.rl.leananalytics.LeanAnalyticsSdk
import com.rl.leananalytics.TrackPageAdapter


class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        LeanAnalyticsSdk.init(this, object : TrackPageAdapter {
            override fun trackActivity(activityName: String) {
                // track here the page to your actual analytics SDK
            }
        })
    }
}