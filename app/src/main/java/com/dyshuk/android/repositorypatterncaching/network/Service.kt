package com.dyshuk.android.repositorypatterncaching.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

interface VideosService {
    @GET("devbytes")
    suspend fun getPlaylist(): NetworkVideoContainer
}

object VideosNetwork {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val videos = retrofit.create(VideosService::class.java)
}