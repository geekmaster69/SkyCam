package com.example.skycam.retrofit

import com.example.skycam.Constans
import com.example.skycam.DataResponseServer
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @FormUrlEncoded
    @POST(Constans.LOGIN_PATH)
     fun login (@Field("user") user: String, @Field("password") password: String): Call<DataResponseServer>

    @FormUrlEncoded
    @POST(Constans.LOGIN_PATH)
    suspend fun loginUser (@Field("user") user: String, @Field("password") password: String): DataResponseServer
}
