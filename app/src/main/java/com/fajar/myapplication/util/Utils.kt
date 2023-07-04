package com.fajar.myapplication.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

object Utils {

    fun toast(context: Context, message: String) = Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()

    fun ImageView.loadImageUrl(url: String?) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }

}