package com.pri.mainproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.widget.Button
import android.widget.Toast

class SelectPage : AppCompatActivity() {
    private var backPressedTime: Long = 0

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
}