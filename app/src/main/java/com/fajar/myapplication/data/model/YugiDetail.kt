package com.fajar.myapplication.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class YugiDetail(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "frame")
    var frame: String? = null,

    @ColumnInfo(name = "desc")
    var desc: String? = null,

    @ColumnInfo(name = "race")
    var race: String? = null,

    @ColumnInfo(name = "archetype")
    var archetype: String? = null,

    @ColumnInfo(name = "image")
    var cardImage: Long? = null,

    @ColumnInfo(name = "atk")
    var atk: Long? = null,

    @ColumnInfo(name = "def")
    var def: Long? = null,

    @ColumnInfo(name = "level")
    var level: String? = null,

    @ColumnInfo(name = "attribute")
    var attribute: String? = null,

    @ColumnInfo(name = "banlistInfo")
    var banlistInfo: String? = null,

    ): Parcelable