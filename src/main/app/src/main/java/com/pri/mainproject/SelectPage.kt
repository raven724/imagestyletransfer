package com.pri.mainproject

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class SelectPage : AppCompatActivity() {
    private var backPressedTime: Long = 0

    private val FLAG_REQUEST_CAMERA = 90

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_page)

        val btnTransfer: Button = findViewById(R.id.btn_transferAction)
        val btnPhoto: Button = findViewById(R.id.btn_PhotoAction)

        btnTransfer.setOnClickListener {
            val transferIntent = Intent(this, Transfer1::class.java)
            startActivity(transferIntent)
        }
        btnPhoto.setOnClickListener {
            val photoIntent = Intent(this, Photo1::class.java)
            startActivity(photoIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                FLAG_REQUEST_CAMERA -> {
                    if(data?.extras?.get("data") != null){
                        val intentPhotoPreview = Intent(this, Photo1::class.java)
                        val bitmap = data.extras?.get("data") as Bitmap
                        val fileName = cacheImageFile(bitmap)
                        intentPhotoPreview.putExtra("fileName", fileName)
                        startActivity(intentPhotoPreview)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        val toast = Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT)
        if(System.currentTimeMillis() > backPressedTime + 2000){
            backPressedTime = System.currentTimeMillis()
            toast.show()
            return
        }
        if(System.currentTimeMillis() <= backPressedTime + 2000){
            moveTaskToBack(true)
            finishAndRemoveTask()
            Process.killProcess(Process.myPid())
        }
    }

    private fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, FLAG_REQUEST_CAMERA)
    }

    private fun cacheImageFile(bitmap: Bitmap): String{
        val fileName = newFileName()
        File.createTempFile(fileName, ".jpg", this.cacheDir)
        val cacheFile = File(this.cacheDir, "$fileName.jpg")
        val outputStream = cacheFile.outputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.close()
        return "$fileName.jpg"
    }

    private fun newFileName(): String{
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA)
        return sdf.format(System.currentTimeMillis())
    }
}