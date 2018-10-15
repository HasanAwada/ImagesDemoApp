package com.example.pc.imagesdemoapplication.ui.image

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.pc.imagesdemoapplication.database.ImageRepository

/**
 * Created by Hasan.Awada on 10/15/2018.
 */
class ImagesViewModelFactory(private val imageRepository: ImageRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            return ImageViewModel(imageRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    
}