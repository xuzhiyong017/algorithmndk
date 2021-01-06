package com.sky.algorithmndk.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * @author: xuzhiyong
 * @date: 21-1-6  上午11:26
 * @Email: 18971269648@163.com
 * @description:
 */
class CustomView : ViewGroup {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    override fun onLayout(
        changed: Boolean,
        l: Int,
        t: Int,
        r: Int,
        b: Int
    ) {
    }
}