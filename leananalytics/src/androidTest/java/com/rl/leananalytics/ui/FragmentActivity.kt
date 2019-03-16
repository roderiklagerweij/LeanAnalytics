package com.rl.leananalytics.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rl.leananalytics.R

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_activity_layout)
    }
}