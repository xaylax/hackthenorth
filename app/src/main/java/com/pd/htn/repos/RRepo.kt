package com.pd.htn.repos

import com.google.gson.JsonObject
import com.pd.htn.MainApplication
import com.pd.htn.services.RServie
import okhttp3.MediaType
import retrofit2.Callback
import okhttp3.MultipartBody
import okhttp3.RequestBody



class RRepo {
    private val service = MainApplication.retrofit2.create(RServie::class.java)

    fun uploadReceipt(image : MultipartBody.Part, callback : Callback<JsonObject>) {
        service.uploadReceipt(image).enqueue(callback)
    }

    fun toMultiPartFile(name: String, byteArray: ByteArray): MultipartBody.Part {
        val reqFile = RequestBody.create(MediaType.parse("image/jpeg"), byteArray)

        return MultipartBody.Part.createFormData(
            name,
            null, // filename, this is optional
            reqFile
        )
    }
}