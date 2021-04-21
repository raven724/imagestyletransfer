package com.pri.mainproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SelectFilter : AppCompatActivity() {
    private var printString: String = ""
    private var colorFilter = ColorFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_filter)

        val btnApply: Button = findViewById(R.id.btn_returnFilter)
        val btnReset: Button = findViewById(R.id.btn_deleteSelect)
        val textView: TextView = findViewById(R.id.textView4)

        btnApply.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("colorFilter", colorFilter.colorMatrix.array)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
        btnReset.setOnClickListener {
            colorFilter.resetMatrix()
            printString = ""
            val resetString = getString(R.string.string_filter_reset)
            textView.text = resetString
        }

        val btnGrayScale: Button = findViewById(R.id.btn_filterGrayScale)
        val btnInvert: Button = findViewById(R.id.btn_filterInvert)
        val btnRedOnly: Button = findViewById(R.id.btn_filterRed)
        val btnBlueOnly: Button = findViewById(R.id.btn_filterBlue)
        val btnGreenOnly: Button = findViewById(R.id.btn_filterGreen)
        val btnSwapRB: Button = findViewById(R.id.btn_filterSwapRB)
        val btnSwapGB: Button = findViewById(R.id.btn_filterSwapGB)
        val btnSwapRG: Button = findViewById(R.id.btn_filterSwapRG)
        val btnSepia: Button = findViewById(R.id.btn_filterSepia)
        val btnWarm: Button = findViewById(R.id.btn_filterWarm)
        val btnCool: Button = findViewById(R.id.btn_filterCool)

        val btnExampleGrayScale: Button = findViewById(R.id.btn_exampleGrayScale)
        val btnExampleInvert: Button = findViewById(R.id.btn_exampleInvert)
        val btnExampleRed: Button = findViewById(R.id.btn_exampleRed)
        val btnExampleBlue: Button = findViewById(R.id.btn_exampleBlue)
        val btnExampleGreen: Button = findViewById(R.id.btn_exampleGreen)
        val btnExampleSwapRB: Button = findViewById(R.id.btn_exampleSwapRB)
        val btnExampleSwapRG: Button = findViewById(R.id.btn_exampleSwapRG)
        val btnExampleSwapGB: Button = findViewById(R.id.btn_exampleSwapGB)
        val btnExampleSepia: Button = findViewById(R.id.btn_exampleSepia)
        val btnExampleWarm: Button = findViewById(R.id.btn_exampleWarm)
        val btnExampleCool: Button = findViewById(R.id.btn_exampleCool)

        btnGrayScale.setOnClickListener {
            printString += "GrayScale "
            colorFilter.addFilter("GrayScale")
            textView.text = printString
        }
        btnInvert.setOnClickListener {
            printString += "Invert "
            colorFilter.addFilter("Invert")
            textView.text = printString
        }
        btnRedOnly.setOnClickListener {
            printString += "Red "
            colorFilter.addFilter("Red")
            textView.text = printString
        }
        btnBlueOnly.setOnClickListener {
            printString += "Blue "
            colorFilter.addFilter("Blue")
            textView.text = printString
        }
        btnGreenOnly.setOnClickListener {
            printString += "Green "
            colorFilter.addFilter("Green")
            textView.text = printString
        }
        btnSwapRB.setOnClickListener {
            printString += "SwapRedAndBlue "
            colorFilter.addFilter("swapRB")
            textView.text = printString
        }
        btnSwapGB.setOnClickListener {
            printString += "SwapGreenAndBlue "
            colorFilter.addFilter("swapGB")
            textView.text = printString
        }
        btnSwapRG.setOnClickListener {
            printString += "SwapRedAndGreen "
            colorFilter.addFilter("swapRG")
            textView.text = printString
        }
        btnSepia.setOnClickListener {
            printString += "Sepia "
            colorFilter.addFilter("Sepia")
            textView.text = printString
        }
        btnWarm.setOnClickListener {
            printString += "WarmColor "
            colorFilter.addFilter("Warm")
            textView.text = printString
        }
        btnCool.setOnClickListener {
            printString += "CoolColor "
            colorFilter.addFilter("Cool")
            textView.text = printString
        }

        btnExampleGrayScale.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "grayscale.jpg")
            exampleIntent.putExtra("model", "GrayScale")
            startActivity(exampleIntent)
        }
        btnExampleInvert.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "invert.jpg")
            exampleIntent.putExtra("model", "Invert")
            startActivity(exampleIntent)
        }
        btnExampleRed.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "redonly.jpg")
            exampleIntent.putExtra("model", "Red Only")
            startActivity(exampleIntent)
        }
        btnExampleBlue.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "blueonly.jpg")
            exampleIntent.putExtra("model", "Blue Only")
            startActivity(exampleIntent)
        }
        btnExampleGreen.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "greenonly.jpg")
            exampleIntent.putExtra("model", "Green Only")
            startActivity(exampleIntent)
        }
        btnExampleSwapRB.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "swapredblue.jpg")
            exampleIntent.putExtra("model", "Swap Red and Blue")
            startActivity(exampleIntent)
        }
        btnExampleSwapRG.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "swapredgreen.jpg")
            exampleIntent.putExtra("model", "Swap Red and Green")
            startActivity(exampleIntent)
        }
        btnExampleSwapGB.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "swapgreenblue.jpg")
            exampleIntent.putExtra("model", "Swap Green and Blue ")
            startActivity(exampleIntent)
        }
        btnExampleSepia.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "sepia.jpg")
            exampleIntent.putExtra("model", "Sepia")
            startActivity(exampleIntent)
        }
        btnExampleWarm.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "warm.jpg")
            exampleIntent.putExtra("model", "Warm")
            startActivity(exampleIntent)
        }
        btnExampleCool.setOnClickListener {
            val exampleIntent = Intent(this, ModelImage::class.java)
            exampleIntent.putExtra("photo", "cool.jpg")
            exampleIntent.putExtra("model", "Cool")
            startActivity(exampleIntent)
        }
    }
}