package com.pri.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SubPage13 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_page13)

        val btnSave: Button = findViewById(R.id.btn_saveFile)
        val btnSub13Main: Button = findViewById(R.id.btn_sub13Main)

        btnSub13Main.setOnClickListener {
            val intentSub13Main = Intent(this, MainActivity::class.java)
            startActivity(intentSub13Main)
        }
    }
}