package com.pri.mainproject

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import java.io.File

class Transfer2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer2)

        val imageView: ImageView = findViewById(R.id.imageOutput)
        val cacheFile = File(this.cacheDir, "output.jpg")
        val fileInputStream = cacheFile.inputStream()
        val drawableOutput = Drawable.createFromStream(fileInputStream, null)
        imageView.setImageBitmap(drawableOutput.toBitmap())
    }
}