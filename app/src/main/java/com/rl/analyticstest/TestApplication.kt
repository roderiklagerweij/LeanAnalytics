package com.rl.analyticstest

import android.app.Application
import com.rl.leananalytics.LeanAnalyticsSdk
import com.rl.leananalytics.TrackPageConfiguration
import com.rl.leananalytics.TrackingAdapter


class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        LeanAnalyticsSdk.run {
            init(this@TestApplication, object : TrackingAdapter {
                override fun trackAction(id: String) {
                    // track here the action to your actual analytics SDK
                }

                override fun trackPage(activityName: String) {
                    // track here the page to your actual analytics SDK
                }
            })
            setTrackPageConfiguration(TrackPageConfiguration.TRACK_ONRESUME)
        }
    }
}