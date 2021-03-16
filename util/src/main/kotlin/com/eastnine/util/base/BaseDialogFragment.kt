package com.eastnine.util.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.annotations.Nullable

/**
 * custom dialog 기본설정
 */
open class BaseDialogFragment: DialogFragment() {
    lateinit var mContext: Context
    private var onDismissListener: DialogInterface.OnDismissListener? = null
    private var isShow = false
    private var isFullScreen = false
    var backgroundColorDrawable = ColorDrawable(Color.TRANSPARENT)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mDialog = super.onCreateDialog(savedInstanceState)

        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog.window?.setBackgroundDrawable(backgroundColorDrawable)
        return mDialog
    }

    override fun onResume() {
        super.onResume()
        
        //전체화면
        if(isFullScreen) {
            dialog?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }

    fun show(manager: FragmentManager?) {
        if(manager != null) {
            show(manager, null)
        }
    }

    fun getContextString(@StringRes resId: Int): String {
        return mContext.getString(resId)
    }

    fun getContextString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return mContext.getString(resId, *formatArgs)
    }

    fun <T : ViewDataBinding> inflateDataBinding(inflater: LayoutInflater,
                                                 layoutId: Int,
                                                 parent: ViewGroup?,
                                                 attachToParent: Boolean = false,
                                                 lifecycleOwner: Boolean = true): T {
        val binding = DataBindingUtil.inflate<T>(inflater, layoutId, parent, attachToParent)
        if(lifecycleOwner) {
            binding.lifecycleOwner = this
        }

        return binding
    }

    fun <T: ViewModel> viewModel(modelClass: Class<T>): T {
        return ViewModelProvider(this).get(modelClass)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if(!isShow) {
            super.show(manager, tag)

            isShow = true

            if(dialog != null && dialog?.window != null) {
                dialog?.window?.decorView?.systemUiVisibility = requireActivity().window.decorView.systemUiVisibility
                dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            }
        }
    }

    override fun dismiss() {
        if(isShow) {
            super.dismiss()

            isShow = false
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        if(onDismissListener != null) {
            onDismissListener!!.onDismiss(dialog)
        }
    }

    fun setOnDismissListener(@Nullable onDismissListener: DialogInterface.OnDismissListener) {
        this.onDismissListener = onDismissListener
    }

    fun setOnDismissListener(listener: (DialogInterface?) -> Unit) {
        this.onDismissListener = DialogInterface.OnDismissListener { dialog -> listener(dialog) }
    }
}