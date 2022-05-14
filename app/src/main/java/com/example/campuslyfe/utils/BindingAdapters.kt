package com.example.campuslyfe.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("goneIf")
fun View.goneIf(condition: Boolean?) {
    visibility =
        if (condition == true)
            View.GONE
        else View.VISIBLE
}