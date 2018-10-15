package com.example.pc.imagesdemoapplication.database.images

import android.arch.persistence.room.*
import io.reactivex.Maybe

/**
 * Created by Hasan.Awada on 10/12/2018.
 */
@Dao
abstract class ImageDao {

    @Query("SELECT * FROM images")
    abstract fun getImages(): Maybe<List<Image>>

    @Query("SELECT * FROM images WHERE id=:imageId")
    abstract fun getImageById(imageId: Int): Maybe<Image>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertImages(images: List<Image>)

    @Update
    abstract fun updateImage(vararg images: Image)

    @Delete
    abstract fun deleteImage(image: Image)

    @Query("DELETE FROM images")
    abstract fun deleteAllImages()

}