package com.eastnine.parknic

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    
    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}