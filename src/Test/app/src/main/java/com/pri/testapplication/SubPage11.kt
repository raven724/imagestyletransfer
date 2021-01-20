package com.pri.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class SubPage11 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_page11)

        val viewInputImage: ImageView = findViewById(R.id.imageInput)
        val btnSelectStyle: Button = findViewById(R.id.btn_modelSelect)
        val btnSub11Sub12: Button = findViewById(R.id.btn_Sub1112)

        btnSelectStyle.setOnClickListener {
            val intentSubPopup: Intent = Intent(this, PopUp_model::class.java)
            startActivity(intentSubPopup)
        }

        btnSub11Sub12.setOnClickListener {
            val intentSub11Sub12: Intent = Intent(this, SubPage12::class.java)
            startActivity(intentSub11Sub12)
        }
    }
}