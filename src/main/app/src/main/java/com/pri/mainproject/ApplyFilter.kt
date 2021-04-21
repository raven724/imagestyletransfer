package com.pri.mainproject

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class ApplyFilter : AppCompatActivity() {

    private val flagRequestStorage = 50
    private val flagRequestFilter = 60
    private val flagRequestBuild = 70

    private var colorFilter = ColorFilter()
    private var outputBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_filter)

        val btnBuildFilter: Button = findViewById(R.id.btn_buildFilter)
        val btnSelectFilter: Button = findViewById(R.id.btn_selectFilter)
        val btnApplyFilter: Button = findViewById(R.id.btn_applyFilter)
        val btnSaveImage: Button = findViewById(R.id.btn_saveFilterImage)
        val imageView: ImageView = findViewById(R.id.filterImage)

        imageView.setImageResource(R.drawable.select_image)

        imageView.setOnClickListener {
            val inputIntent = Intent(Intent.ACTION_PICK)
            inputIntent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(inputIntent, flagRequestStorage)
        }

        btnBuildFilter.setOnClickListener {
            val buildIntent = Intent(this, BuildFilter::class.java)
            startActivityForResult(buildIntent, flagRequestBuild)
        }

        btnSelectFilter.setOnClickListener {
            val selectIntent = Intent(this, SelectFilter::class.java)
            startActivityForResult(selectIntent, flagRequestFilter)
        }

        btnApplyFilter.setOnClickListener {
            if(colorFilter.inputBitmap != null){
                outputBitmap = colorFilter.applyColor(colorFilter.inputBitmap!!)
                imageView.setImageBitmap(outputBitmap)
            }
            else{
                Toast.makeText(this, "Please Select Photo", Toast.LENGTH_SHORT).show()
            }
        }

        btnSaveImage.setOnClickListener {
            if(outputBitmap != null){
                saveImage(outputBitmap!!)
            }
            else{
                Toast.makeText(this, "Please apply the filter first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                flagRequestStorage -> {
                    if(data?.data != null){
                        val uri = data.data
                        val imageView: ImageView = findViewById(R.id.filterImage)
                        imageView.setImageURI(uri)
                        colorFilter.inputBitmap = getBitmap(uri!!)
                    }
                }
                flagRequestBuild -> {
                    if(data?.getFloatArrayExtra("buildFilter") != null){
                        val returnArray = data.getFloatArrayExtra("buildFilter")
                        colorFilter.applyFilter(returnArray!!)
                    }
                }
                flagRequestFilter -> {
                    if(data?.getFloatArrayExtra("colorFilter") != null){
                        val returnArray = data.getFloatArrayExtra("colorFilter")
                        colorFilter.applyFilter(returnArray!!)
                    }
                }
            }
        }
    }

    private fun getBitmap(uri: Uri): Bitmap {
        return when{
            Build.VERSION.SDK_INT > 27 -> {
                val source = ImageDecoder.createSource(this.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }
            else -> MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
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
                Toast.makeText(this, "Error detected: descriptor", Toast.LENGTH_SHORT).show()
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                values.clear()
                values.put(MediaStore.Images.Media.IS_PENDING, 0)
                contentResolver.update(uri, values, null, null)
            }
            Toast.makeText(this, "Finish saved file", Toast.LENGTH_SHORT).show()
        }
        if(uri == null){
            Toast.makeText(this, "Error detected: uri", Toast.LENGTH_SHORT).show()
        }
    }

    private fun makeNewFileName(): String{
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA)
        val filename = sdf.format(System.currentTimeMillis())

        return "$filename.jpeg"
    }
}