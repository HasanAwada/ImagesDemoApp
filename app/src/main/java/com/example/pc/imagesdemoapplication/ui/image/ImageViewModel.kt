package com.example.pc.imagesdemoapplication.ui.image

import android.arch.lifecycle.ViewModel
import com.example.pc.imagesdemoapplication.database.images.ImageRepository
import com.example.pc.imagesdemoapplication.database.images.Image
import io.reactivex.Observable


/**
 * Created by Hasan.Awada on 10/12/2018.
 */
class ImageViewModel(private val imageRepository: ImageRepository) : ViewModel() {
    fun getImages(): Observable<List<Image>>? {
        return imageRepository.getImages()
    }
}