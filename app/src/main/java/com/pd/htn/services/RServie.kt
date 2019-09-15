package com.pd.htn.services

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RServie {
    @Multipart
    @POST("https://clever-elephant-29.localtunnel.me")
    fun uploadReceipt(@Part file : MultipartBody.Part) : Call<JsonObject>
}