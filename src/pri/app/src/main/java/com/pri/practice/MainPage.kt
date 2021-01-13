package com.pri.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val btnSubpage1: Button = findViewById(R.id.btn_main_sub1)
        val btnSubpage2: Button = findViewById(R.id.btn_main_sub2)

        btnSubpage1.setOnClickListener {
            val intentSubpage1 = Intent(this, SubActivity1::class.java)
            startActivity(intentSubpage1)
        }

        btnSubpage2.setOnClickListener {
            val intentSubpage2 = Intent(this, SubActivity2::class.java)
            startActivity(intentSubpage2)
        }
    }
}