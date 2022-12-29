package com.example.skycam.data.network.model

import com.example.skycam.Constans
import com.google.gson.annotations.SerializedName

data class UserInfo (
     @SerializedName("user")var user: String = "",
     @SerializedName("password")var password: String = ""

)