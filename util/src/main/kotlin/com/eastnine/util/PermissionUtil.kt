package com.eastnine.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object PermissionUtil {
    interface OnPermissionListener {
        fun allowedPermissions()
        fun notAllowedPermissions(permissions: Array<out String>)
    }

    //권한에 따른 분기
    fun permissionsCheckListener(context: Context, permissions: Array<out String>, permissionListener: OnPermissionListener) =
        permissionArray(context, permissions).run {
            if(isEmpty()) {
                permissionListener.allowedPermissions()
            } else {
                permissionListener.notAllowedPermissions(this)
            }
        }

    //권한에 따른 분기
    fun permissionsCheckListener(context: Context, permissions: Array<out String>,
                                 allowedListener: () -> Unit, notAllowedListener: (Array<out String>) -> Unit) =
        permissionArray(context, permissions).run {
            if(isEmpty()) {
                allowedListener()
            } else {
                notAllowedListener(this)
            }
        }


    //권한배열 확인
    private fun permissionArray(context: Context, permissions: Array<out String>): Array<String> =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissions.filter { ActivityCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED }.toTypedArray()
        } else {
            arrayOf()
        }

    //권한 확인
    fun checkPermission(context: Context, permission: String): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }

    //권한배열 확인
    fun checkPermissions(context: Context, permissions: Array<String>): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            !(permissions.isEmpty() || permissions.any { !checkPermission(context, it) })
        } else {
            true
        }

    //단일 권한 설정
    fun verifyPermissions(grantResults: Int): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            grantResults == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }

    //권한배열 설정
    fun verifyPermissions(grantResults: IntArray): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            grantResults.isEmpty() || grantResults.any { it == PackageManager.PERMISSION_GRANTED }
        } else {
            true
        }
}