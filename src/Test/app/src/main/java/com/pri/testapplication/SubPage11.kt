package com.pri.testapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.core.net.toUri

class SubPage11 : AppCompatActivity() {
    var modelName = "undefined"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_page11)

        // val viewInputImage: ImageView = findViewById(R.id.imageInput)
        val btnSelectStyle: Button = findViewById(R.id.btn_callModel)
        val btnSub11Sub12: Button = findViewById(R.id.btn_Sub1112)
        val imageInput: ImageView = findViewById(R.id.imageInput)

        val debugHasExtra: Boolean = intent.hasExtra("photoUri")
        Log.d("Sub11", "hasExtra: ${debugHasExtra.toString()}")

        if(intent.hasExtra("photoUri")){
            val stringUri = intent.getStringExtra("photoUri")
            val photoUri = stringUri!!.toUri()
            imageInput.setImageURI(photoUri)
        }

        imageInput.setOnClickListener {
            val intentGallery = Intent()
        }

        btnSelectStyle.setOnClickListener {
            val intentSubPopup = Intent(this, PopUp_model::class.java)
            startActivityForResult(intentSubPopup, 50)
        }

        btnSub11Sub12.setOnClickListener {
            val intentSub11Sub12 = Intent(this, SubPage12::class.java)
            intentSub11Sub12.putExtra("modelType", modelName)
            startActivity(intentSub11Sub12)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                50 -> modelName = data?.getStringExtra("returnValue").toString()
            }
        }
    }
}