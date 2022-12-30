package com.example.skycam.retrofit

import com.example.skycam.Constans
import com.example.skycam.DataResponseServer
import com.example.skycam.data.network.model.UnitsEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @FormUrlEncoded
    @POST(Constans.CAMERAS_PATH)
    suspend fun getCamerasApi(
        @Field("units") units: String
    ): UnitsEntity

    @FormUrlEncoded
    @POST(Constans.LOGIN_PATH)
    suspend fun loginUser (@Field("user") user: String, @Field("password") password: String): DataResponseServer
}
