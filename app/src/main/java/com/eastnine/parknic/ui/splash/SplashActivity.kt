package com.eastnine.parknic.ui.splash

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.eastnine.parknic.R
import com.eastnine.parknic.databinding.ActivitySplashBinding
import com.eastnine.parknic.ui.main.MainActivity
import com.eastnine.util.PermissionUtil
import com.eastnine.util.base.BaseActivity
import kotlinx.coroutines.*

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermission()
    }

    private fun checkPermission() {
        PermissionUtil.permissionsCheckListener(this@SplashActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            {
                startTimer()
            },
            { permissions ->
                val permissionRationale = permissions.filter { shouldShowRequestPermissionRationale(it) }.toTypedArray()
                if (permissionRationale.isNotEmpty()) {
                    permissionDialogShow(permissionRationale)
                } else {
                    requestPermission(permissions)
                }
            }
        )
    }

    private fun permissionDialogShow(permissions: Array<String>) {
        AlertDialog.Builder(this@SplashActivity).run {
            setMessage(getString(R.string.not_allow_permission))

            setPositiveButton(getString(R.string.dialog_permission_ok_button)) { dialog, _ ->
                dialog.dismiss()

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    permissions.any { shouldShowRequestPermissionRationale(it) }) {
                    activityResultRegistry
                        .register("PERMISSION", ActivityResultContracts.StartActivityForResult())
                        {
                            checkPermission()
                        }
                        .launch(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null)))
                } else {
                    checkPermission()
                }
            }

            setNegativeButton(getString(R.string.dialog_permission_cancel_button)) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
        }.show()
    }

    private fun requestPermission(permissions: Array<out String>) =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            if (result.filter { it.value }.isNotEmpty()) {
                startTimer()
            }
        }.launch(permissions)

    private fun startTimer() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SPLASH_DELAY_TIME)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val SPLASH_DELAY_TIME = 3000L
    }
}