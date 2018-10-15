package com.example.pc.imagesdemoapplication.utilities

import com.example.pc.imagesdemoapplication.services.ImageRetrofitService
import com.example.pc.imagesdemoapplication.services.RetrofitClient

/**
 * Created by Hasan.Awada on 10/12/2018.
 */
object ApiUtils {
    val imageService: ImageRetrofitService?
        get() = RetrofitClient.getClient().create(ImageRetrofitService::class.java)
}