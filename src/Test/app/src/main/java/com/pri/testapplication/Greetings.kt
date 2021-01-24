package com.pri.testapplication

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Greetings : AppCompatActivity() {
    val STORAGE_PERMISIION = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
    android.Manifest.permission.READ_EXTERNAL_STORAGE)
    val FLAG_PERM_STORAGE = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greetings)

        if(checkPermission(STORAGE_PERMISIION, FLAG_PERM_STORAGE)){
            val intentGreetMain = Intent(this, MainActivity::class.java)
            startActivity(intentGreetMain)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            FLAG_PERM_STORAGE -> {
                for(grant in grantResults){
                    if (grant != PackageManager.PERMISSION_GRANTED){
                        val intentGreetPermError = Intent(this, PermissionError::class.java)
                        startActivity(intentGreetPermError)
                    }
                }
                val intentGreetMain = Intent(this, MainActivity::class.java)
                startActivity(intentGreetMain)
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
}