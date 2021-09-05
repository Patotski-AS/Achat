package com.progerchat.achat.screens.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class ProfileViewModel(var emailChiper:String, var storageImage:String, var placeStorage:Boolean) : ViewModel() {

    // e-mail закрытый для скачивания картинки
    // байты картинки

    private var _bmp: MutableLiveData<Bitmap?>? = null
    val bmp: LiveData<Bitmap?>? get() = _bmp!!

    private var _progress: MutableLiveData<Int>? = null
    val progress: LiveData<Int>? get() = _progress

    // место хранения картинки
    // нужно ли подгружать картинку с сервера

    private val ONE_MEGABYTE: Long = 1024 * 1024

    init {

        _bmp = MutableLiveData<Bitmap?>()
        _progress = MutableLiveData<Int>()

        _progress!!.value = 0

        if (!placeStorage){
               // get local storage
        }
        else
            getIconInternet(emailChiper)

    }

    fun getIconInternet(str: String) {

        val ref: StorageReference = FirebaseStorage.getInstance().reference.child("images").child(str)

        ref.getBytes(ONE_MEGABYTE).addOnSuccessListener {

            _bmp?.value = BitmapFactory.decodeByteArray(it, 0, it.size)

            try {
                val fileCash = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "$emailChiper")
                val outCash = FileOutputStream(fileCash)
                _bmp!!.value?.compress(Bitmap.CompressFormat.JPEG , 100, outCash)
                outCash.flush()
                outCash.close()
                placeStorage = false
            }
            catch (e: Exception){
                print(e.message)
            }
        }

    }

    fun push(uri: Uri, emailChiper: String) {

        val ref: StorageReference = FirebaseStorage.getInstance().reference.child("images").child("$emailChiper")
        ref.putFile(uri)
            .addOnProgressListener { taskSnapshot ->
                _progress?.value = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                    .totalByteCount).toInt()
            }

    }
}