package com.example.mertkesgin.weatherapp.core

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageHelper : IImageProcess {

    private val mPicassoInstance = Picasso.get()

    override fun loadUrl(url: String, imageView: ImageView) {
        mPicassoInstance.load(url ?: "").into(
            imageView,
            object : Callback.EmptyCallback() {

                override fun onError(e: Exception?) {
                    loadUrl(url, imageView)
                }
            }
        )
    }
}
