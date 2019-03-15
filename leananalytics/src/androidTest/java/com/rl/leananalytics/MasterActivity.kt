package com.rl.leananalytics

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.master_activity_layout.*

class MasterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.master_activity_layout)

        navigate_to_detail_button.setOnClickListener { startActivity(Intent(this@MasterActivity, DetailActivity::class.java)) }
    }
}