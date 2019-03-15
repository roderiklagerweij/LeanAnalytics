package com.rl.analyticstest

import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.rl.leananalytics.clicktracker.IdClickInjector

/**
 * Created by Roderik on 23-05-18.
 */

abstract class BaseActivity  : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            IdClickInjector.inject(window.decorView as ViewGroup)
        }, 1000)

    }
}