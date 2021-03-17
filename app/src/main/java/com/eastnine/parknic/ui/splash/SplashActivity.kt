package com.eastnine.parknic.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.eastnine.domain.dto.ParkingDto
import com.eastnine.parknic.R
import com.eastnine.parknic.databinding.ActivitySplashBinding
import com.eastnine.parknic.ui.MainActivity
import com.eastnine.util.base.BaseActivity
import com.kakao.util.maps.helper.Utility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setupTimer()
        setupListener()
        setupParkingData()
    }

    private fun setupTimer() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SPLASH_DELAY_TIME)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun setupListener() {
        viewModel.setOnSplashDataListener(object: OnSplashDataListener {
            override fun getParking(parkingList: List<ParkingDto>) {
                parkingList.size
            }
        })
    }

    private fun setupParkingData() {
        viewModel.getParking()
    }

    companion object {
        private const val SPLASH_DELAY_TIME = 3000L
    }
}