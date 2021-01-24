package com.pri.testapplication

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class SubPage21 : AppCompatActivity() {

    val CAMERA_PERMISSION = arrayOf(android.Manifest.permission.CAMERA)
    val STORAGE_PERMISSION = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val FLAG_PERM_CAMERA = 98
    val FLAG_PERM_STORAGE = 99
    val FLAG_REQ_CAMERA = 101
    val FLAG_REQ_STORAGE = 102
    val FLAG_REQ_TRANSFER = 103

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_page21)

        if(checkPermission(CAMERA_PERMISSION, FLAG_PERM_STORAGE)){
            openCamera()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            FLAG_PERM_CAMERA -> {
                for(grant in grantResults){
                    if (grant != PackageManager.PERMISSION_GRANTED){
                        val intentSub21PermError = Intent(this, PermissionError::class.java)
                        startActivity(intentSub21PermError)
                        return
                    }
                }
                openCamera()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                FLAG_REQ_CAMERA -> {
                    if(data?.extras?.get("data") != null){
                        val intentSub21Sub22 = Intent(this, SubPage22::class.java)
                        val bitmap = data.extras?.get("data") as Bitmap
                        val uri = saveImageFile(newFileName(), "image/jpg", bitmap)
                        val stringUri = uri.toString()
                        intentSub21Sub22.putExtra("photoUri", stringUri)
                        startActivity(intentSub21Sub22)
                        Log.d("Sub21", "Sub21 resume after intent2122")
                    }
                }
            }
        }
    }

    fun checkPermission(permissions: Array<out String>, flag: Int): Boolean{
        for(permission in permissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, permissions, flag)
                return false
            }
        }
        return true
    }

    fun openCamera(){
        if(checkPermission(CAMERA_PERMISSION, FLAG_PERM_CAMERA)){
            val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intentCamera, FLAG_REQ_CAMERA)
        }
    }

    fun saveImageFile(filename: String, mimeType: String, bitmap: Bitmap): Uri?{
        var values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            values.put(MediaStore.Images.Media.IS_PENDING, 1)
        }
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        if(uri != null){
            var descriptor = contentResolver.openFileDescriptor(uri, "w")
            if(descriptor != null){
                var fos = FileOutputStream(descriptor.fileDescriptor)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.close()
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                values.clear()
                values.put(MediaStore.Images.Media.IS_PENDING, 0)
                contentResolver.update(uri, values, null, null)
            }
        }
        return uri
    }

    fun newFileName(): String{
        val sdf = SimpleDateFormat("yyyy<<dd_HHmmss", Locale.KOREA)
        val filename = sdf.format(System.currentTimeMillis())

        return "$filename.jpeg"
    }
}