package com.example.alwaysremeber.functions.mainpage

import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setImage")
fun setImage(button: Button, drawable : Drawable){
        button.background = drawable
}

@BindingAdapter("setText")
fun setText(textView: TextView, categoryName : String){
    textView.text = categoryName
}