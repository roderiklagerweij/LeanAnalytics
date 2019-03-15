package com.rl.leananalytics

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class LeanAnalyticsSdk {
    companion object {

        const val TAG = "LeanAnalyticsSdk"

        fun init(application : Application, trackPageAdapter : TrackPageAdapter) {
            application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {

                }

                override fun onActivityResumed(activity: Activity?) {

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
                    activity?.let {
                        Log.d(TAG, "tracking page: ${it.localClassName}")
                        trackPageAdapter.trackActivity(it.localClassName)
                    }
                }
            })
        }
    }
}