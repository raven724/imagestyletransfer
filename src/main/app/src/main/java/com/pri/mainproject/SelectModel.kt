package com.pri.mainproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process.killProcess
import android.provider.MediaStore
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import org.checkerframework.common.value.qual.IntRangeFromNonNegative

class SelectModel : AppCompatActivity() {
    private var returnString: String = "None"
    private var returnUri: String = "None"
    private val flagStorage = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_model)

        val textView: TextView = findViewById(R.id.modelText)
        val btnModelPhoto1: Button = findViewById(R.id.btn_model1)
        val btnDefaultPhoto: Button = findViewById(R.id.btn_defaultModel)
        val btnClosePage: Button = findViewById(R.id.btn_selectModel)

        btnModelPhoto1.setOnClickListener {
            returnString = "style23.jpg"
            textView.text = getString(R.string.string_model1)
        }
        btnDefaultPhoto.setOnClickListener {
            returnString = "default"
            textView.text = getString(R.string.string_default_model)
        }
        btnClosePage.setOnClickListener {
            when(returnString){
                "None" -> {
                    Toast.makeText(this, "NoNoNo!!!!!!!", Toast.LENGTH_SHORT).show()
                }
                "default" -> {
                    Toast.makeText(this, "Select photo to use as model", Toast.LENGTH_SHORT).show()
                    val storageIntent = Intent(Intent.ACTION_PICK)
                    storageIntent.type = MediaStore.Images.Media.CONTENT_TYPE
                    startActivityForResult(storageIntent, flagStorage)
                }

                else -> {
                    val returnIntent = Intent()
                    returnIntent.putExtra("returnValue", this.returnString)
                    returnIntent.putExtra("returnUri", this.returnUri)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                flagStorage -> {
                    val uri = data?.data
                    returnUri = uri.toString()
                    if(returnString == "None" && returnUri == "None"){
                        Toast.makeText(this, "No!!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val returnIntent = Intent()
                        returnIntent.putExtra("returnValue", this.returnString)
                        returnIntent.putExtra("returnUri", this.returnUri)
                        setResult(Activity.RESULT_OK, returnIntent)
                        finish()
                    }
                }
            }
        }
    }
}