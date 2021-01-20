package com.pri.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SubPage12 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_page12)

        val btnSub12Sub13: Button = findViewById(R.id.btn_Sub1213)

        btnSub12Sub13.setOnClickListener {
            val intentSub12Sub13 = Intent(this, SubPage13::class.java)
            startActivity(intentSub12Sub13)
        }
    }
}