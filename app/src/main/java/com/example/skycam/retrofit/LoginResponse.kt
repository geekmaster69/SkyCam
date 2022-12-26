package com.example.skycam.retrofit

import com.example.skycam.Constans
import com.example.skycam.DataEntity
import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName(Constans.STATUS_PROPERTY)var status: String,
    @SerializedName(Constans.DATA_PROPERTY)var data: String )