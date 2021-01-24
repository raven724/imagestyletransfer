package com.pri.testapplication

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSubpage1: Button = findViewById(R.id.btn_main_sub1)
        val btnSubpage2: Button = findViewById(R.id.btn_main_sub2)

        btnSubpage1.setOnClickListener {
            val indentMainSub1: Intent = Intent(this, SubPage11::class.java)
            startActivity(indentMainSub1)
        }

        btnSubpage2.setOnClickListener {
            val indentMainSub2: Intent = Intent(this, SubPage21::class.java)
            startActivity(indentMainSub2)
        }
    }
}