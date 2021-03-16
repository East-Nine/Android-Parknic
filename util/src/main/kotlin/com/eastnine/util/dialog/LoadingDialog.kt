package com.eastnine.util.dialog

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.eastnine.util.R
import com.eastnine.util.base.BaseDialogFragment

/**
 * 로딩 Dialog
 */
class LoadingDialog(private var loadingImage: Drawable? = null,
                    private var loadingText: String?): BaseDialogFragment() {
    private var progressBar: ProgressBar? = null
    private var textView: TextView? = null

    override fun onStart() {
        super.onStart()

        val layoutParams = dialog?.window!!.attributes
        layoutParams.dimAmount = 0.5f
        dialog?.window!!.attributes = layoutParams
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isCancelable = false

        if(loadingImage == null) {
            loadingImage = ContextCompat.getDrawable(mContext, R.drawable.ic_loading_completed_progress)
        }

        val linearLayout = LinearLayout(mContext)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        progressBar = ProgressBar(mContext, null, android.R.attr.progressBarStyle)
        progressBar!!.indeterminateDrawable = loadingImage

        val progressLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        progressLayoutParams.gravity = Gravity.CENTER
        progressBar!!.layoutParams = progressLayoutParams

        linearLayout.addView(progressBar)

        if(loadingText != null) {
            textView = TextView(mContext)
            textView!!.setTextColor(Color.WHITE)
            textView!!.textSize = 12f
            textView!!.text = loadingText

            val textLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            textLayoutParams.gravity = Gravity.CENTER
            textLayoutParams.topMargin = 13
            textView!!.layoutParams = textLayoutParams

            linearLayout.addView(textView)
        }

        return linearLayout
    }

    fun setLoadingDrawable(loadingImage: Drawable) {
        this.loadingImage = loadingImage
        progressBar?.indeterminateDrawable = loadingImage
    }

    fun setLoadingText(loadingText: String) {
        this.loadingText = loadingText
        textView?.text = loadingText
    }
}