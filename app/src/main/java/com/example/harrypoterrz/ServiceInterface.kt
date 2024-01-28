package com.example.harrypoterrz

import ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ServiceInterface {

    //@Headers("Content-Type: application/json")
    @GET("/api/characters")
    fun getAllCharacter(): Call<List<ApiResponse>>
}
