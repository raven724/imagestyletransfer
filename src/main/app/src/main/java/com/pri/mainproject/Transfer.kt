package com.pri.mainproject

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import com.pri.mainproject.ml.MagentaArbitraryImageStylizationV1256Int8Transfer1
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class Transfer(private val context: Context) {
    private var transferModel = MagentaArbitraryImageStylizationV1256Int8Transfer1.newInstance(context)

    fun runTransferModel(styleBottleNeck: TensorBuffer, inputBitmap: Bitmap): Bitmap?{
        val preProcessInput = inputBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val transferImage = TensorImage.fromBitmap(preProcessInput)
        val outputTransfer = transferModel.process(transferImage, styleBottleNeck)
        val styledImage = outputTransfer.styledImageAsTensorImage

        return styledImage.bitmap
    }

    fun finish(){
        transferModel.close()
    }
}