package com.pri.mainproject

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap

class ModelImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model_image)
        val imageView: ImageView = findViewById(R.id.imageView)
        val printString: String? = intent.getStringExtra("photo")
        val assetManager = this.assets
        val fileDescriptor = assetManager.openFd(printString!!)
        val inputStream = fileDescriptor.createInputStream()
        val drawableInput = Drawable.createFromStream(inputStream, null)
        imageView.setImageBitmap(drawableInput.toBitmap())
    }
}