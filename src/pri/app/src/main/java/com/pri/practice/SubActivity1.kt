package com.pri.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SubActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub1)

        val btnSubpage1: Button = findViewById(R.id.btn_sub1_main)
        val btnSubpage2: Button = findViewById(R.id.btn_sub1_sub1_1)

        btnSubpage1.setOnClickListener {
            val intentSubpage1 = Intent(this, MainPage::class.java)
            startActivity(intentSubpage1)
        }

        btnSubpage2.setOnClickListener {
            val intentSubpage2 = Intent(this, SubActivity1_1::class.java)
            startActivity(intentSubpage2)
        }
    }
}

// startActivityResult()를 통해서 더 편리한 방식으로 개선할 수 있을 것으로 보임.