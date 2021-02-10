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
    private var inputFile: Bitmap? = null
    private var transferModel = MagentaArbitraryImageStylizationV1256Int8Transfer1.newInstance(context)

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

    fun runTransferModel(styleBottleNeck: TensorBuffer): Bitmap?{
        return if(inputFile != null){
            val transferImage = TensorImage.fromBitmap(inputFile)
            val outputTransfer = transferModel.process(transferImage, styleBottleNeck)

            outputTransfer.styledImageAsTensorImage.bitmap
        }
        else{
            Toast.makeText(context, "NoNoNoNo!!!!", Toast.LENGTH_LONG).show()
            null
        }
    }
}