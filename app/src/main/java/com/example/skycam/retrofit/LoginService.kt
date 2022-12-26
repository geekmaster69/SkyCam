package com.example.skycam.retrofit

import android.content.Context
import com.example.skycam.Constans
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @Headers("Content-Type: application/json",)
    @POST(Constans.LOGIN_PHAT)
    suspend fun login(@Body data: UserInfo): LoginResponse
}
