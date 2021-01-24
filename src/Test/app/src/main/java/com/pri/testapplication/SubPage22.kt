package com.pri.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri

class SubPage22 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_page22)

        val image: ImageView = findViewById(R.id.imageView3)
        val btnTransfer: Button = findViewById(R.id.btn_Sub22Sub11)
        if(intent.hasExtra("photoUri")){
            val stringUri = intent.getStringExtra("photoUri")
            val photoUri = stringUri!!.toUri()
            image.setImageURI(photoUri)
        }
        else{
            Toast.makeText(this, "Error get image from Sub21", Toast.LENGTH_LONG).show()
        }

        btnTransfer.setOnClickListener {
            val intentSub22Sub11 = Intent(this, SubPage11::class.java)
            val stringUri = intent.getStringExtra("photoUri")
            intentSub22Sub11.putExtra("photoUri", stringUri)
            startActivity(intentSub22Sub11)
        }
    }
}