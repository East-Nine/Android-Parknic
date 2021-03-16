package com.eastnine.util.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * inputSignatureFragment 기본설정
 */
open class BaseFragment: Fragment() {
    lateinit var mContext: Context
    lateinit var mActivity: FragmentActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as FragmentActivity
    }

    fun <T : ViewDataBinding> inflateDataBinding(inflater: LayoutInflater,
                                                 layoutId: Int,
                                                 parent: ViewGroup?,
                                                 attachToParent: Boolean = false,
                                                 lifecycleOwner: Boolean = true): T {
        val binding = DataBindingUtil.inflate<T>(inflater, layoutId, parent, attachToParent)
        if(lifecycleOwner) {
            binding.lifecycleOwner = viewLifecycleOwner
        }

        return binding
    }

    fun <T: ViewModel> viewModel(modelClass: Class<T>): T {
        return ViewModelProvider(this).get(modelClass)
    }

    fun <T: ViewModel> viewModel(modelClass: Class<T>, factory: ViewModelProvider.Factory): T {
        return ViewModelProvider(this, factory).get(modelClass)
    }

    fun getContextString(@StringRes resId: Int): String {
        return mContext.getString(resId)
    }

    fun getContextString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return mContext.getString(resId, *formatArgs)
    }
}