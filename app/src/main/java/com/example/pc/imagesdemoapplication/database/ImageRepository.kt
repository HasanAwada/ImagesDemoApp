package com.example.pc.imagesdemoapplication.database

import com.example.pc.imagesdemoapplication.database.images.Image
import com.example.pc.imagesdemoapplication.database.images.ImageDao
import com.example.pc.imagesdemoapplication.services.ImageRetrofitService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by Hasan.Awada on 10/12/2018.
 */
class ImageRepository(private val imageDao: ImageDao, private val imageRetrofitService: ImageRetrofitService?) {

    fun getImages(): Observable<List<Image>>? {
        try {

            return getImagesFromAPI()
            /*return Observable.concatArray(
                    getImagesFromLocalDB(),
                    getImagesFromAPI()?.materialize()
                            ?.filter { !it.isOnError }
                            ?.dematerialize<List<Image>>()
            )*/
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    private fun getImagesFromLocalDB(): Observable<List<Image>>? {
        val list = imageDao?.getImages()?.toObservable()
        return list
    }

    private fun getImagesFromAPI(): Observable<List<Image>>? {
        try {
            val list = imageRetrofitService?.getImages()
                    /*?.doOnNext {
                        try {
                            storeImagesToDB(it)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    ?.map { it -> it }*/
            return list
        } catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    private fun storeImagesToDB(images: List<Image>) {
        Observable.fromCallable {
            try {
                imageDao?.insertImages(images)
            } catch (e: ConcurrentModificationException) {
                e.printStackTrace()
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {}
    }


}