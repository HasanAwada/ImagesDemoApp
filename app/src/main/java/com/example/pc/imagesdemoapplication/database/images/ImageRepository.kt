package com.example.pc.imagesdemoapplication.database.images

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
//            return getImagesFromAPI()?.doOnComplete {
//                getImagesFromLocalDB()
//            }

//            return getImagesFromLocalDB()

//            return getImagesFromAPI()

            return Observable.concatArray(
                    getImagesFromAPI(),
                    getImagesFromLocalDB()
                    )

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun getImagesFromLocalDB(): Observable<List<Image>>? {
        return imageDao?.getImages()?.toObservable()
    }

    private fun getImagesFromAPI(): Observable<List<Image>>? {
        return imageRetrofitService?.getImages()?.map {
            it.distinctBy {
                it.Id
            }
        }?.doOnNext {
            try {
                storeImagesToDB(it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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