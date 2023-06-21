package com.app.wallpaperapp.webapi

import com.app.wallpaperapp.webapi.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

   // @GET("/api/?key=37309411-fe5f3d44facc80b63b845c092&q=flowers&image_type=photo")
   @GET("/api")
    suspend fun getQuotes( @Query("key")  key:String,
                           @Query ("q")   question:String,
                           @Query("image_type") imageType:String) : Response<Result>
    }




