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
    private var sendString: String = "None"
    private var modelString: String = "None"
    private val flagStorage = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_model)

        val textView: TextView = findViewById(R.id.modelText)
        val btnModelPhoto1: Button = findViewById(R.id.btn_model1)
        val btnModelPhoto2: Button = findViewById(R.id.btn_model2)
        val btnModelPhoto3: Button = findViewById(R.id.btn_model3)
        val btnDefaultPhoto: Button = findViewById(R.id.btn_defaultModel)
        val btnClosePage: Button = findViewById(R.id.btn_selectModel)
        val btnViewModel1: Button = findViewById(R.id.btn_lookupModel1)
        val btnViewModel2: Button = findViewById(R.id.btn_lookupModel2)
        val btnViewModel3: Button = findViewById(R.id.btn_lookupModel3)

        btnModelPhoto1.setOnClickListener {
            returnString = "style23.jpg"
            textView.text = getString(R.string.string_model1)
        }
        btnModelPhoto2.setOnClickListener {
            returnString = "Starry_Night.jpg"
            textView.text = getString(R.string.string_model2)
        }
        btnModelPhoto3.setOnClickListener {
            returnString = "Mona_Lisa.jpg"
            textView.text = getString(R.string.string_model3)
        }
        btnDefaultPhoto.setOnClickListener {
            returnString = "default"
            textView.text = getString(R.string.string_default_model)
        }
        btnViewModel1.setOnClickListener {
            sendString = "style23.jpg"
            modelString = "Udnie, Francis Picabia"
            val sendIntent = Intent(this, ModelImage::class.java)
            sendIntent.putExtra("photo", sendString)
            sendIntent.putExtra("model", modelString)
            startActivity(sendIntent)
        }
        btnViewModel2.setOnClickListener {
            sendString = "Starry_Night.jpg"
            modelString = "Starry Night, Vincent Van Gogh"
            val sendIntent = Intent(this, ModelImage::class.java)
            sendIntent.putExtra("photo", sendString)
            sendIntent.putExtra("model", modelString)
            startActivity(sendIntent)
        }
        btnViewModel3.setOnClickListener {
            sendString = "Mona_Lisa.jpg"
            modelString = "Mona_Lisa, Leonardo da Vinci"
            val sendIntent = Intent(this, ModelImage::class.java)
            sendIntent.putExtra("photo", sendString)
            sendIntent.putExtra("model", modelString)
            startActivity(sendIntent)
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