package com.toscano.test1.data.network.endpoints

import com.toscano.test1.data.network.entities.jikan.top.TopAnimes
import retrofit2.Response
import retrofit2.http.GET

interface TopAnimesEndPoint {
    @GET("top/anime")
    suspend fun getAllTopAnimes() : Response<TopAnimes>
}