package com.eastnine.util

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter

val Int.dp: Int
    get() {
        val metrics = Resources.getSystem().displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics)
            .toInt()
    }

@BindingAdapter("android:layout_marginBottom")
fun setLayoutMarginBottom(view: View, value: Int) {
    view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
        bottomMargin = value.dp
    }
}