package com.example.pc.imagesdemoapplication.database.images

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Created by Hasan.Awada on 10/16/2018.
 */
@Entity(tableName = "images")
class Image {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    var Id: Int = 0

    @NotNull
    @ColumnInfo(name = "link")
    var link: String? = null

    @NotNull
    @ColumnInfo(name = "title")
    var title: String? = null

    @NotNull
    @ColumnInfo(name = "description")
    var description: String? = null

    override fun toString(): String {
        return "$Id - $title"
    }

}