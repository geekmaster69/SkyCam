package com.example.skycam.retrofit

import com.example.skycam.Constans
import com.example.skycam.DataResponseServer
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @POST(Constans.LOGIN_PHAT)
     fun login (@Body data: UserInfo): Call<DataResponseServer>
}
