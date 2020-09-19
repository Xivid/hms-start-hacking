package com.huawei.hackzurich

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.huawei.hackzurich.R.layout.preference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.better_me.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        btn_better_me.setOnClickListener {
            setContentView(R.layout.better_me)
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

