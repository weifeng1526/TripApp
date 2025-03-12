package com.example.tripapp.ui.feature.trip.plan.create

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.example.tripapp.ui.feature.trip.dataObjects.convertLongToDate
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

fun isBlankOrEmptyTextField(value: String): Boolean {
    return value.isBlank() || value.isEmpty()
}

fun isKeepError(isError: Boolean, errorFlag: Boolean, clearFlag: (Boolean) -> Unit): Boolean {
    var isKeep = false
    if (errorFlag) {
        if (isError) isKeep = true
        else {
            clearFlag(isError)
            isKeep = false
        }
    }
    return isKeep
}

fun checkDateRange(start: Long?, end: Long?, onResult: (Int) -> Unit) {
    val current = System.currentTimeMillis()
    if (start == null && end == null) onResult(1)
    if (end == null) onResult(2)
    if (start != null && end != null) {
        if (end < current) onResult(3)
        else onResult(0)
    }
}

fun convertDateRangeToString(start: Long?, end: Long?): String {
    val result: String
    if (start != null && end != null) {
        result = "${convertLongToDate(start)} ~ ${convertLongToDate(end)}"
    } else result = ""
    return result
}

fun uriToByteArray(context: Context, uri: Uri): ByteArray? {
    return try {
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            val byteArrayOutputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, length)
            }
            byteArrayOutputStream.toByteArray()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun bitmapToMultipartBodyPart(bitmap: Bitmap, partName: String): MultipartBody.Part {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    val requestBody = byteArray.toRequestBody("image/png".toMediaTypeOrNull(), 0, byteArray.size)
    return MultipartBody.Part.createFormData(partName, "image.png", requestBody)
}

fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    // 將 Bitmap 壓縮為 JPEG 格式，並寫入到 ByteArrayOutputStream 中
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}