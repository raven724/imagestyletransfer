package com.pri.mainproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.FileOutputStream

class Transfer1 : AppCompatActivity() {
    private val flagRequestStorage = 50
    private val flagRequestModel = 51

    private val imageView: ImageView = findViewById(R.id.imageInput)
    private var inputBitmap: Bitmap? = null
    private var modelData: Bitmap? = null

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

        btnMainPage.setOnClickListener {
            finish()
        }

        btnTransferPage.setOnClickListener {
            val predictor = Predict(this)
            if(inputBitmap != null && modelData != null) {
                predictor.getInputFile(modelData!!)
                val predictResult: TensorBuffer? = predictor.runPredictModel()
                if (predictResult != null){
                    val transferModel = Transfer(this)
                    transferModel.getInputFile(inputBitmap!!)
                    val transferResult = transferModel.runTransferModel(predictResult)
                    // imageView.setImageBitmap(transferResult)
                    File.createTempFile("output", ".jpg", this.cacheDir)
                    val cacheFile = File(this.cacheDir, "output.jpg")
                    val outputStream = cacheFile.outputStream()
                    transferResult!!.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.close()
                    val nextIntent = Intent(this, Transfer2::class.java)
                    startActivity(nextIntent)
                }
                else{
                    Toast.makeText(this, "JGVHJBGVJHB!", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(this, "NoNoNoNoNoNoNoNoNo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                flagRequestStorage -> {
                    val uri = data?.data
                    this.inputBitmap = transferUriToBitmap(uri!!)
                    imageView.setImageURI(uri)
                }
                flagRequestModel -> {
                    val returnString = data?.getStringExtra("returnValue")
                    modelData = if(returnString == "default"){
                        // need to implement.
                        null
                    }
                    else{
                        val assetManager = this.assets
                        val fileDescriptor = assetManager.openFd(returnString!!)
                        val inputStream = fileDescriptor.createInputStream()
                        val drawableInput = Drawable.createFromStream(inputStream, null)
                        drawableInput.toBitmap()
                    }
                }
            }
        }
    }

    private fun transferUriToBitmap(uri: Uri): Bitmap{
         return when {
            Build.VERSION.SDK_INT > 27 -> {
                val source = ImageDecoder.createSource(this.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }
            else -> MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        }
    }
}