package com.huawei.hackzurich

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.preference.*

class PreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preference)

        next.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


}