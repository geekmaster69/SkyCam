package com.example.skycam.data.network.model

data class DataCamera(
    val channels: List<Channel>,
    val company: String,
    val idCamera: String,
    val nameCamera: String,
    val online: String,
    val plate: String,
    val subCompany: String,
    val totalCamaras: String
)