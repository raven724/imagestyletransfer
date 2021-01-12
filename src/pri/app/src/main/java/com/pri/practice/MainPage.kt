package com.pri.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val subBtn1: Button = findViewById(R.id.btn_sub1)
        subBtn1.setOnClickListener {
            val intentSubMove1 = Intent(this, SubPage::class.java)
            startActivity(intentSubMove1)
        }
    }
}