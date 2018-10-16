package com.example.pc.imagesdemoapplication.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.pc.imagesdemoapplication.database.images.Image
import com.example.pc.imagesdemoapplication.database.images.ImageDao

/**
 * Created by Hasan.Awada on 10/16/2018.
 */
@Database(entities = [(Image::class)], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao

    companion object {
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, ApplicationDatabase::class.java, "Images_Database")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE as ApplicationDatabase
        }
    }

}