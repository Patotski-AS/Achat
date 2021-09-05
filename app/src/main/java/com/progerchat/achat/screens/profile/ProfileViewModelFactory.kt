package com.progerchat.achat.screens.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProfileViewModelFactory (
    private val emailChiper:String,
    private val storageImage:String,
    private val placeStorage:Boolean
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(emailChiper, storageImage, placeStorage) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
