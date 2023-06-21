package com.app.wallpaperapp.webapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

   // private const val BASE_URL = "https://pixabay.com/api/?key=37309411-fe5f3d44facc80b63b845c092&q=flowers&image_type=photo"
   private const val BASE_URL = "https://pixabay.com"

    fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}