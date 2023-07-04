package com.fajar.myapplication.data.remote

import com.fajar.myapplication.data.response.YugiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("cardinfo.php/name")
    fun getCard(
        @Query("name") query: String
    ): Call<YugiResponse>

    @GET("cardinfo.php")
    fun getDetailCard(
        @Query("name") name: String
    ): Call<YugiResponse>

}