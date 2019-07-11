package com.josephcobbinah.alcphaseonechallenge

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ActivityA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "ALC 4 Phase 1"

        btnToActivityB.setOnClickListener {
            startActivity(Intent(this,ActivityB::class.java))
        }

        btnToActivityC.setOnClickListener {
            startActivity(Intent(this,ActivityC::class.java))
        }
    }
}
