package com.huawei.hackzurich

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.huawei.hms.common.ApiException
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Note that the Toolbar defined in the layout has the id "my_toolbar"
//        setSupportActionBar(findViewById(R.id.my_toolbar))

/*
        val checkStatusTextView = findViewById<TextView>(R.id.main_check)

        lifecycle.coroutineScope.launchWhenCreated {
            try {
                delay(1000)
                runHmsConfigurationCheck()
                checkStatusTextView.text = getString(R.string.checking_setup_result_ok)
            } catch (checkException: Exception) {
                checkStatusTextView.text =
                    getString(R.string.checking_setup_result_fail, checkException.message)
            }
        }
*/
        btn_auto_order.setOnClickListener {
            startActivity(Intent(this, SwipeActivity::class.java))
        }

        btn_better_me.setOnClickListener {
            startActivity(Intent(this, PreferenceActivity::class.java))
        }

        btn_show_me.setOnClickListener {
            startActivity(Intent(this, ShowMeActivity::class.java))
        }

        btn_co2.setOnClickListener {
            startActivity(Intent(this, CO2Activity::class.java))
        }
    }

    private suspend fun runHmsConfigurationCheck() {
        testHmsCorePresence()
        testAccountByRequestingPushNotificationsToken()
    }

    private suspend fun testAccountByRequestingPushNotificationsToken() {
        val pushToken = withContext(Dispatchers.IO) {
            HmsUtils.getPushNotificationsToken(this@MainActivity)
        }
        check(pushToken.isNotEmpty()) { "Push notifications token retrieved, but empty. Clear app data and try again." }
    }

    private fun testHmsCorePresence() {
        check(HmsUtils.isHmsAvailable(this)) { "Please make sure you have HMS Core installed on the test device." }
    }
}

