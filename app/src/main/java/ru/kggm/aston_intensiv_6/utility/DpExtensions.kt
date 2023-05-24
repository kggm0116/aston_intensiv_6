package ru.kggm.aston_intensiv_6.utility

import android.content.Context
import android.view.View

fun Context.dip2px(value: Float) = resources.displayMetrics.density * value

fun View.dip2px(value: Float) = resources.displayMetrics.density * value