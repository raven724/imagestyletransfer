package com.pri.mainproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectPage : AppCompatActivity() {
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
}