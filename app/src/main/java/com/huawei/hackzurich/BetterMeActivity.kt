package com.huawei.hackzurich

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.better_me.*

class BetterMeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.better_me)

        btn_get_fit.setOnClickListener {
            startActivity(Intent(this, PreferenceActivity::class.java))
        }

        btn_stay_fit.setOnClickListener {
            startActivity(Intent(this, PreferenceActivity::class.java))
        }
    }
}