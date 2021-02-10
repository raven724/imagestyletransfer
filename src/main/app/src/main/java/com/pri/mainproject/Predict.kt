package com.pri.mainproject

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.pri.mainproject.ml.MagentaArbitraryImageStylizationV1256Int8Prediction1
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class Predict(private val context: Context) {
    private var inputFile: Bitmap? = null
    private var predictModel = MagentaArbitraryImageStylizationV1256Int8Prediction1.newInstance(context)

    fun getInputFile(uri: Uri){
        this.inputFile = when {
            Build.VERSION.SDK_INT > 27 -> {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }
            else -> MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    }

    fun getInputFile(bitmap: Bitmap){
        this.inputFile = bitmap
    }

    fun runPredictModel(): TensorBuffer?{
        return if(inputFile != null){
            val styleImage = TensorImage.fromBitmap(inputFile)
            val outputPredict = predictModel.process(styleImage)

            outputPredict.styleBottleneckAsTensorBuffer
        }
        else{
            Toast.makeText(context, "NoNoNoNo!!!!", Toast.LENGTH_LONG).show()
            null
        }
    }
}