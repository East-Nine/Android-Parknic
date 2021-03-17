package com.eastnine.parknic.ui

import android.os.Bundle
import com.eastnine.parknic.R
import com.eastnine.parknic.databinding.ActivityMainBinding
import com.eastnine.util.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}