package com.example.mertkesgin.weatherapp.ui.current_weather

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.mertkesgin.weatherapp.core.ImageHelper

object CurrentWeatherBindings {

    @BindingAdapter("android:setImage")
    @JvmStatic
    fun getImage(imageview: ImageView, image: String?) {
        image?.let { ImageHelper().loadUrl(it, imageview) }
    }
}
