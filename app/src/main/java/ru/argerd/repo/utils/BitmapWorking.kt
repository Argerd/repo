package ru.argerd.repo.utils

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import ru.argerd.repo.App.Companion.context
import ru.argerd.repo.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class BitmapWorking {
    fun saveBitmapToFileFromGallery(data: Uri): Boolean {
        val bitmap: Bitmap?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(
                        context.contentResolver,
                        data))
            } catch (e: Exception) {
                return false
            }
        } else {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        context.contentResolver,
                        data)
            } catch (e: IOException) {
                return false
            }
        }
        val file = File(context.filesDir.toString() + "/",
                context.resources.getString(R.string.file_name_for_profile))
        try {
            val fos = FileOutputStream(file)
            bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            return false
        }
        return true
    }
}
