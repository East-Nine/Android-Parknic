package com.eastnine.util.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.eastnine.util.R

abstract class BaseActivity<VDB : ViewDataBinding>(
    @LayoutRes private val layoutResourceId: Int
    ) : AppCompatActivity() {

    protected lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<VDB>(this, layoutResourceId).apply {
            lifecycleOwner = this@BaseActivity
        }
    }
    
    fun checkPermissions(vararg permissions: String, listener: (Boolean) -> Unit) {
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionMap ->
            val noGrantedPermissions = permissionMap.filter { !it.value }
            val isGranted = noGrantedPermissions.isNullOrEmpty()
    
            if (isGranted) {
                listener(true)
            } else {
                val keyArray = noGrantedPermissions.keys.toTypedArray()
    
                var permissionText = ""
                for (key in keyArray) {
                    if (permissionText.isNotEmpty()) {
                        permissionText += ", "
                    }
                    permissionText += noGrantedPermissions[key]
                }
                permissionDialogShow(this@BaseActivity, getString(R.string.not_allow_permission, permissionText), *permissions) {
                    listener(it)
                }
            }
        }.launch(permissions)
    }
    
    private fun permissionDialogShow(context: Context, message: String, vararg permissions: String, listener: (Boolean) -> Unit) {
        val permissionDialog = AlertDialog.Builder(context)
        permissionDialog.setMessage(message)
        permissionDialog.setPositiveButton(getString(R.string.dialog_permission_ok_button)) { dialog, _ ->
            dialog.dismiss()
    
            checkPermissions(*permissions) {
                listener(it)
            }
        }
        permissionDialog.setNegativeButton(getString(R.string.dialog_permission_cancel_button)) { dialog, _ ->
            dialog.dismiss()
            listener(false)
        }
        permissionDialog.show()
    }
}
