package com.example.dataemployee.network

import com.example.dataemployee.model.DataApi
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getAllUsers(): Call<DataApi>

}