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

    fun runPredictModel(bitmap: Bitmap): TensorBuffer{
        val styleImage = TensorImage.fromBitmap(bitmap)
        val outputPredict = predictModel.process(styleImage)

        return outputPredict.styleBottleneckAsTensorBuffer
    }

    fun finish(){
        predictModel.close()
    }
}