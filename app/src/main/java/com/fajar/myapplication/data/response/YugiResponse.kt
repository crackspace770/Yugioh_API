package com.fajar.myapplication.data.response

import com.google.gson.annotations.SerializedName

data class YugiResponse (
    val data: List<Datum>
)

data class Datum (
    val id: Long,
    val name: String,
    val type: String,
    val frameType: String,
    val desc: String,
    val race: String,
    val archetype: String? = null,
    val cardImages: String,
    val atk: Long? = null,
    val def: Long? = null,
    val level: Long? = null,
    val attribute: String? = null,
    val banlistInfo: String? = null
)



