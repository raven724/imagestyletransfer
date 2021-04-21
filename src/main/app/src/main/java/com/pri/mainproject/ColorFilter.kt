package com.pri.mainproject

import android.graphics.*
import android.graphics.ColorMatrix

class ColorFilter {
    var colorMatrix: ColorMatrix = ColorMatrix()
    var inputBitmap: Bitmap? = null

    private val grayScale = floatArrayOf(
            0.2989f, 0.5870f, 0.1140f, 0.0f, 0.0f,
            0.2989f, 0.5870f, 0.1140f, 0.0f, 0.0f,
            0.2989f, 0.5870f, 0.1140f, 0.0f, 0.0f,
            0.0000f, 0.0000f, 0.0000f, 1.0f, 0.0f
            )

    private val invert = floatArrayOf(
            -1.0f, 0.0f, 0.0f, 0.0f, 255.0f,
            0.0f, -1.0f, 0.0f, 0.0f, 255.0f,
            0.0f, 0.0f, -1.0f, 0.0f, 255.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
            )

    private val redOnly = floatArrayOf(
            1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    )

    private val greenOnly = floatArrayOf(
            0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    )

    private val blueOnly = floatArrayOf(
            0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    )

    private val swapRB = floatArrayOf(
            0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    )

    private val swapGB = floatArrayOf(
            1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    )

    private val swapRG = floatArrayOf(
            0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    )

    private val sepia = floatArrayOf(
            0.393f, 0.769f, 0.189f, 0.0f, 0.0f,
            0.349f, 0.686f, 0.168f, 0.0f, 0.0f,
            0.272f, 0.534f, 0.131f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    )

    private val warm = floatArrayOf(
            1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.780f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.605f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    )

    private val cool = floatArrayOf(
            0.765f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.814f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    )

    private val reset = floatArrayOf(
            1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f
    )

    init{
        colorMatrix = ColorMatrix(reset)
    }

    fun resetMatrix(){
        colorMatrix = ColorMatrix(reset)
    }

    fun addFilter(string: String){
        var postConcat = ColorMatrix()
        when(string){
            "GrayScale" -> {
                postConcat = ColorMatrix(grayScale)
            }
            "Invert" -> {
                postConcat = ColorMatrix(invert)
            }
            "Red" -> {
                postConcat = ColorMatrix(redOnly)
            }
            "Green" -> {
                postConcat = ColorMatrix(greenOnly)
            }
            "Blue" -> {
                postConcat = ColorMatrix(blueOnly)
            }
            "swapRB" -> {
                postConcat = ColorMatrix(swapRB)
            }
            "swapRG" -> {
                postConcat = ColorMatrix(swapRG)
            }
            "swapGB" -> {
                postConcat = ColorMatrix(swapGB)
            }
            "Sepia" -> {
                postConcat = ColorMatrix(sepia)
            }
            "Warm" -> {
                postConcat = ColorMatrix(warm)
            }
            "Cool" -> {
                postConcat = ColorMatrix(cool)
            }
        }
        colorMatrix.postConcat(postConcat)
    }

    fun applyFilter(floatArray: FloatArray){
        colorMatrix = ColorMatrix(floatArray)
    }

    fun applyColor(bitmap: Bitmap): Bitmap{
        val outPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        outPaint.setColorFilter(ColorMatrixColorFilter(colorMatrix))
        val outBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val inBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val testCanvas = Canvas(outBitmap)
        testCanvas.drawBitmap(inBitmap, 0.0f, 0.0f, outPaint)
        return outBitmap
    }
}