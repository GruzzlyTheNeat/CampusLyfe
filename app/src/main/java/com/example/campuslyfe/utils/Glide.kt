package com.example.campuslyfe.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.campuslyfe.R
import kotlinx.coroutines.NonCancellable.start

fun ImageView.downloadFromURL(url : String?, placeholderprogressBar : CircularProgressDrawable){
    val option = RequestOptions.placeholderOf(placeholderprogressBar).error(R.mipmap.ic_launcher)
    Glide.with(context).setDefaultRequestOptions(option).load(url)
        .into(this)

}

@SuppressLint("ResourceAsColor")
fun placeHolderProgressBar(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}