package com.pri.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SubActivity1_3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_activity1_3)

        val btnSubpage1: Button = findViewById(R.id.btn_save_photo)
        val btnSubpage2: Button = findViewById(R.id.btn_sub_1_3_main)

        btnSubpage2.setOnClickListener {
            val intentSubpage2 = Intent(this, MainPage::class.java)
            startActivity(intentSubpage2)
        }
    }
}