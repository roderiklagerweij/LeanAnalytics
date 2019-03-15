package com.rl.analyticstest

import android.app.Application
import com.rl.leananalytics.LeanAnalyticsSdk
import com.rl.leananalytics.TrackPageAdapter
import com.rl.leananalytics.TrackPageConfiguration


class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        LeanAnalyticsSdk.run {
            init(this@TestApplication, object : TrackPageAdapter {
                override fun trackActivity(activityName: String) {
                    // track here the page to your actual analytics SDK
                }
            })
            setTrackPageConfiguration(TrackPageConfiguration.TRACK_ONRESUME)
        }
    }
}