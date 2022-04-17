package com.example.mvvmtraining.model.remote

import com.example.mvvmtraining.utils.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SongService {
    @GET(END_POINT)
    fun getSongs(@Query(PARAM_TERM)term:String,
                  @Query(PARAM_MEDIA_TYPE)mediaType:String,
                  @Query(PARAM_ENTITY)entity:String,
                  @Query(PARAM_LIMIT)limit:String): Call<SongResponse>

    companion object{
        fun initRetrofit(): SongService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SongService::class.java)
        }
    }
}