package com.example.skycam.retrofit

import com.example.skycam.Constans
import com.google.gson.annotations.SerializedName

data class UserInfo (
    @SerializedName(Constans.USER_PARAM) val user: String,
    @SerializedName(Constans.PASSWORD_PARAM) val pass: String
)