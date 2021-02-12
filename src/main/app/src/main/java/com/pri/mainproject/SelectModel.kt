package com.pri.mainproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process.killProcess
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.checkerframework.common.value.qual.IntRangeFromNonNegative

class SelectModel : AppCompatActivity() {
    private var returnString: String = "None"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_model)

        val textView: TextView = findViewById(R.id.modelText)
        val btnModelPhoto1: Button = findViewById(R.id.btn_model1)
        val btnClosePage: Button = findViewById(R.id.btn_selectModel)

        btnModelPhoto1.setOnClickListener {
            returnString = "style23.jpg"
            textView.text = getString(R.string.string_model1)
        }
        btnClosePage.setOnClickListener {
            val returnIntent = Intent()
            if(returnString == "None"){
                Toast.makeText(this, "NoNoNo!!!!!!!", Toast.LENGTH_SHORT).show()
            }
            else {
                returnIntent.putExtra("returnValue", this.returnString)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }
    }
}