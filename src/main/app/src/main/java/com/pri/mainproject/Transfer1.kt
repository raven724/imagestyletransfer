package com.pri.mainproject

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class Transfer1 : AppCompatActivity() {
    private val flagRequestStorage = 50
    private val flagRequestModel = 51

    private val imageView: ImageView = findViewById(R.id.imageInput)
    private var inputUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer1)

        val btnSelectModel: Button = findViewById(R.id.btn_model)
        val btnMainPage: Button = findViewById(R.id.btn_mainpage)
        val btnTransferPage: Button = findViewById(R.id.btn_transfer)

        imageView.setOnClickListener {
            val storageIntent = Intent(Intent.ACTION_PICK)
            storageIntent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(storageIntent, flagRequestStorage)
        }

        btnSelectModel.setOnClickListener {
            val modelIntent = Intent(this, SelectModel::class.java)
            startActivityForResult(modelIntent, flagRequestModel)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                flagRequestStorage -> {
                    val uri = data?.data
                    inputUri = uri
                    imageView.setImageURI(uri)
                }
                flagRequestModel -> {
                    val modelData = data?.data
                }
            }
        }
    }
}