package com.rl.analyticstest

import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup

/**
 * Created by Roderik on 23-05-18.
 */

abstract class BaseActivity  : AppCompatActivity() {

    override fun onResume() {
        super.onResume()

        IdClickInjector.inject(window.decorView as ViewGroup)
    }
}