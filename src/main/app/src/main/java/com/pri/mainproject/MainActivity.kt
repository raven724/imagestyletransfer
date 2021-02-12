package com.pri.mainproject

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    val STORAGE_PERMISSION = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)

    val FULL_PERMISSION = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.CAMERA)

    val FLAG_PERMISSION_CAMERA = 90
    val FLAG_PERMISSION_STORAGE = 91
    val FLAG_PERMISSION_FULL = 92

    var RESPOND_PERMISSION_STORAGE = false
    var RESPOND_PERMISSION_CAMERA = false
    var RESPOND_PERMISSION_FULL = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(checkPermission(FULL_PERMISSION, FLAG_PERMISSION_FULL)){
            goMainPage()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            FLAG_PERMISSION_CAMERA -> {
                for(grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "permission denied: Camera", Toast.LENGTH_LONG).show()
                        finish()
                        return
                    }
                }
                RESPOND_PERMISSION_CAMERA = true
            }
            FLAG_PERMISSION_STORAGE -> {
                for(grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "permission denied: Storage", Toast.LENGTH_LONG).show()
                        finish()
                        return
                    }
                }
                RESPOND_PERMISSION_STORAGE = true
            }
            FLAG_PERMISSION_FULL -> {
                for(grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                        finish()
                        return
                    }
                }
                goMainPage()
            }
        }
    }

    private fun checkPermission(permissions: Array<out String>, flag: Int): Boolean{
        Log.d("Greeting", "checkPermission activated: $flag")
        for(permission in permissions){
            if(ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED){
                        Log.d("Greeting", "ActivityCompat activated: $flag")
                ActivityCompat.requestPermissions(this, permissions, flag)
                return false
            }
        }
        return true
    }

    private fun goMainPage(){
        val nextPage = Intent(this, SelectPage::class.java)
        startActivity(nextPage)
    }
}