package com.example.skycam.data.network

import com.example.skycam.Constans
import com.example.skycam.DataResponseServer
import com.example.skycam.data.network.model.UnitsEntity
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CamApiClient {
    @FormUrlEncoded
    @POST(Constans.LOGIN_PATH)
    suspend fun login (
        @Field("user") user: String,
        @Field("password") password: String
    ): DataResponseServer

    @FormUrlEncoded
    @POST(Constans.CAMERAS_PATH)
    suspend fun getCamerasApi(
        @Field("units") units: String
    ): UnitsEntity
}