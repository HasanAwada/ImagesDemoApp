package com.example.pc.imagesdemoapplication

import android.app.Application
import com.example.pc.imagesdemoapplication.database.ApplicationDatabase
import com.example.pc.imagesdemoapplication.database.images.ImageRepository
import com.example.pc.imagesdemoapplication.utilities.ApiUtils

/**
 * Created by Hasan.Awada on 10/12/2018.
 */
class ImagesDemoApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        val imageDao = ApplicationDatabase.getDatabase(this).imageDao()
        val imageRetrofitService = ApiUtils.imageService
        imageRepository = ImageRepository(imageDao, imageRetrofitService)
    }

    companion object {
        var imageRepository: ImageRepository? = null

        fun injectImageRepository(): ImageRepository {
            return imageRepository!!
        }
    }
}