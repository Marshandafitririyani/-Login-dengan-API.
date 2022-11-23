package com.example.coreandroid.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("phone") phone: String?,
        @Field("password") password: String?
    ): String
}