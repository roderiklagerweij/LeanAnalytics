package com.rl.leananalytics

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.rl.leananalytics.clicktracker.IdClickInjector

class LeanAnalyticsSdk {
    companion object {

        const val TAG = "LeanAnalyticsSdk"

        private var trackPageConfiguration : TrackPageConfiguration = TrackPageConfiguration.TRACK_ONCREATE

        fun init(application : Application, trackingAdapter : TrackingAdapter) {
            application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {

                }

                override fun onActivityResumed(activity: Activity?) {
                    if (trackPageConfiguration == TrackPageConfiguration.TRACK_ONRESUME) {
                        trackActivity(activity, trackingAdapter)
                    }
                    IdClickInjector.inject(activity?.window?.decorView as ViewGroup, trackingAdapter)
                }

                override fun onActivityStarted(activity: Activity?) {

                }

                override fun onActivityDestroyed(activity: Activity?) {
                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

                }

                override fun onActivityStopped(activity: Activity?) {

                }

                override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                    if (trackPageConfiguration == TrackPageConfiguration.TRACK_ONCREATE) {
                        trackActivity(activity, trackingAdapter)
                    }
                }
            })
        }

        fun setTrackPageConfiguration(trackPageConfiguration: TrackPageConfiguration) {
            this.trackPageConfiguration = trackPageConfiguration
        }

        private fun trackActivity(activity : Activity?, trackingAdapter: TrackingAdapter) {
            activity?.let {
                Log.d(TAG, "tracking page: ${it.localClassName}")
                trackingAdapter.trackPage(it.localClassName)
            }
        }
    }
}