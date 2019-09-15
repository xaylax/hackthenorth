package com.pd.htn.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.pd.htn.repos.RRepo
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainViewModel : ViewModel() {
    private val repo = RRepo()

    val receiptResponse : LiveData<JsonObject> = MutableLiveData()

    fun uploadReceipt(image: ByteArray) {
        val requestFile = image.toRequestBody("image/jpeg".toMediaTypeOrNull(), 0, image.size)


        repo.uploadReceipt(MultipartBody.Part.createFormData("receipt", null, requestFile ), object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                (receiptResponse as MutableLiveData).postValue(response.body())
            }
        })
    }
}