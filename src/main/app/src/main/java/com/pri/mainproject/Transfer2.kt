package com.pri.mainproject

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class Transfer2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer2)

        val imageView: ImageView = findViewById(R.id.imageOutput)
        val cacheFile = File(this.cacheDir, "output.jpg")
        val fileInputStream = cacheFile.inputStream()
        val drawableOutput = Drawable.createFromStream(fileInputStream, null)
        imageView.setImageBitmap(drawableOutput.toBitmap())

        val btnGotoMainPage: Button = findViewById(R.id.btn_FinishMainpage)
        val btnImageSave: Button = findViewById(R.id.btn_saveImage)

        btnGotoMainPage.setOnClickListener {
            val mainIntent = Intent(this, SelectPage::class.java)
            startActivity(mainIntent)
        }

        btnImageSave.setOnClickListener {
            saveImage(drawableOutput.toBitmap())
        }
    }

    private fun saveImage(bitmap: Bitmap){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, makeNewFileName())
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            values.put(MediaStore.Images.Media.IS_PENDING, 1)
        }
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        if(uri != null){
            val descriptor = contentResolver.openFileDescriptor(uri, "w")
            if(descriptor != null){
                val fos = FileOutputStream(descriptor.fileDescriptor)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.close()
            }
            else{
                Toast.makeText(this, "히히 못가: descriptor", Toast.LENGTH_SHORT).show()
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                values.clear()
                values.put(MediaStore.Images.Media.IS_PENDING, 0)
                contentResolver.update(uri, values, null, null)
            }
            Toast.makeText(this, "히히 됐다", Toast.LENGTH_SHORT).show()
        }
        if(uri == null){
            Toast.makeText(this, "히히 못가: uri", Toast.LENGTH_SHORT).show()
        }
    }

    private fun makeNewFileName(): String{
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA)
        val filename = sdf.format(System.currentTimeMillis())

        return "$filename.jpeg"
    }
}