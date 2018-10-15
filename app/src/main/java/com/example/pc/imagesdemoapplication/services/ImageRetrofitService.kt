package com.example.pc.imagesdemoapplication.services

import com.example.pc.imagesdemoapplication.database.images.Image
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Hasan.Awada on 10/12/2018.
 */
interface ImageRetrofitService {

    @GET("testapi/api/Initialization/SelectAllImages")
    fun getImages(): Observable<List<Image>>

}