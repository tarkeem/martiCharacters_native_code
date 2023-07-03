package com.example.myapplication
import retrofit2.Call
import retrofit2.http.GET
interface myapi {
    @GET("character/")
    fun getData():Call<charactermodel>
}