package com.toscano.test1.data.network.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {

    private const val JIKAN_URL = "https://api.jikan.moe/v4/"
    fun getRetrofitJikanConncetion(): Retrofit{

        //Realizacion de la conexion de nuestra API
        return Retrofit.Builder().baseUrl("https://api.jikan.moe/v4/").addConverterFactory(GsonConverterFactory.create()).build()
    }
}