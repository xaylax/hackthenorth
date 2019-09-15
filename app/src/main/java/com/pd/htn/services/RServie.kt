package com.pd.htn.services

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Part

interface RServie {
    @POST("")
    fun uploadReceipt(@Part file : MultipartBody.Part) : Call<JsonObject>
}