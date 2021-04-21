package com.pri.mainproject

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.addTextChangedListener

class BuildFilter : AppCompatActivity() {
    private var floatArray = floatArrayOf(
        1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 1.0f, 0.0f
    )
    private var colorFilter = ColorFilter()
    private var inputImage: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_build_filter)

        val imageView: ImageView = findViewById(R.id.imageView2)
        val assetManager = this.assets
        val fileDescriptor = assetManager.openFd("mohamed-nohassi-odxB5oIG_iA-unsplash.jpg")
        val inputStream = fileDescriptor.createInputStream()
        val drawableInput = Drawable.createFromStream(inputStream, null)
        inputImage = drawableInput.toBitmap()
        imageView.setImageBitmap(drawableInput.toBitmap())

        val edit11: EditText = findViewById(R.id.edit11)
        val edit12: EditText = findViewById(R.id.edit12)
        val edit13: EditText = findViewById(R.id.edit13)
        val edit14: EditText = findViewById(R.id.edit14)
        val edit15: EditText = findViewById(R.id.edit15)

        val edit21: EditText = findViewById(R.id.edit21)
        val edit22: EditText = findViewById(R.id.edit22)
        val edit23: EditText = findViewById(R.id.edit23)
        val edit24: EditText = findViewById(R.id.edit24)
        val edit25: EditText = findViewById(R.id.edit25)

        val edit31: EditText = findViewById(R.id.edit31)
        val edit32: EditText = findViewById(R.id.edit32)
        val edit33: EditText = findViewById(R.id.edit33)
        val edit34: EditText = findViewById(R.id.edit34)
        val edit35: EditText = findViewById(R.id.edit35)

        val edit41: EditText = findViewById(R.id.edit41)
        val edit42: EditText = findViewById(R.id.edit42)
        val edit43: EditText = findViewById(R.id.edit43)
        val edit44: EditText = findViewById(R.id.edit44)
        val edit45: EditText = findViewById(R.id.edit45)

        edit11.addTextChangedListener {
            val inputString = edit11.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(0, inputString.toFloat())
            }
        }
        edit12.addTextChangedListener {
            val inputString = edit12.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(1, inputString.toFloat())
            }
        }
        edit13.addTextChangedListener {
            val inputString = edit13.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(2, inputString.toFloat())
            }
        }
        edit14.addTextChangedListener {
            val inputString = edit14.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(3, inputString.toFloat())
            }
        }
        edit15.addTextChangedListener {
            val inputString = edit15.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(4, inputString.toFloat())
            }
        }
        edit21.addTextChangedListener {
            val inputString = edit21.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(5, inputString.toFloat())
            }
        }
        edit22.addTextChangedListener {
            val inputString = edit22.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(6, inputString.toFloat())
            }
        }
        edit23.addTextChangedListener {
            val inputString = edit23.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(7, inputString.toFloat())
            }
        }
        edit24.addTextChangedListener {
            val inputString = edit24.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(8, inputString.toFloat())
            }
        }
        edit25.addTextChangedListener {
            val inputString = edit25.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(9, inputString.toFloat())
            }
        }
        edit31.addTextChangedListener {
            val inputString = edit31.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(10, inputString.toFloat())
            }
        }
        edit32.addTextChangedListener {
            val inputString = edit32.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(11, inputString.toFloat())
            }
        }
        edit33.addTextChangedListener {
            val inputString = edit33.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(12, inputString.toFloat())
            }
        }
        edit34.addTextChangedListener {
            val inputString = edit34.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(13, inputString.toFloat())
            }
        }
        edit35.addTextChangedListener {
            val inputString = edit35.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(14, inputString.toFloat())
            }
        }
        edit41.addTextChangedListener {
            val inputString = edit41.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(15, inputString.toFloat())
            }
        }
        edit42.addTextChangedListener {
            val inputString = edit42.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(16, inputString.toFloat())
            }
        }
        edit43.addTextChangedListener {
            val inputString = edit43.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(17, inputString.toFloat())
            }
        }
        edit44.addTextChangedListener {
            val inputString = edit44.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(18, inputString.toFloat())
            }
        }
        edit45.addTextChangedListener {
            val inputString = edit45.text.toString()
            if(inputString.isNotEmpty()){
                changeFilter(19, inputString.toFloat())
            }
        }

        val btnReset: Button = findViewById(R.id.btn_resetBuild)
        val btnApply: Button = findViewById(R.id.btn_applyBuild)

        btnReset.setOnClickListener {
            floatArray = floatArrayOf(
                1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f
            )
            edit11.setText(R.string.string_fixed_one)
            edit22.setText(R.string.string_fixed_one)
            edit33.setText(R.string.string_fixed_one)
            edit44.setText(R.string.string_fixed_one)
            edit12.setText(R.string.string_fixed_zero)
            edit13.setText(R.string.string_fixed_zero)
            edit14.setText(R.string.string_fixed_zero)
            edit15.setText(R.string.string_fixed_zero)
            edit21.setText(R.string.string_fixed_zero)
            edit23.setText(R.string.string_fixed_zero)
            edit24.setText(R.string.string_fixed_zero)
            edit25.setText(R.string.string_fixed_zero)
            edit31.setText(R.string.string_fixed_zero)
            edit32.setText(R.string.string_fixed_zero)
            edit34.setText(R.string.string_fixed_zero)
            edit35.setText(R.string.string_fixed_zero)
            edit41.setText(R.string.string_fixed_zero)
            edit42.setText(R.string.string_fixed_zero)
            edit43.setText(R.string.string_fixed_zero)
            edit45.setText(R.string.string_fixed_zero)
        }

        btnApply.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("buildFilter", floatArray)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    private fun changeFilter(idx: Int, value: Float){
        floatArray[idx] = value
        colorFilter.applyFilter(floatArray)
        val imageView: ImageView = findViewById(R.id.imageView2)
        imageView.setImageBitmap(colorFilter.applyColor(inputImage!!))
    }
}