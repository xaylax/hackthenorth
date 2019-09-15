//package com.pd.htn.vm
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.pd.htn.models.Receipt
//import com.pd.htn.repos.TDRepository
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MainViewModel : ViewModel() {
//    private val repo = TDRepository()
//
//    val receipt : LiveData<Receipt> = MutableLiveData()
//
//    fun uploadReceipt(byteArray: ByteArray) {
//        repo.uploadReceipt(byteArray, object : Callback<Receipt> {
//            override fun onFailure(call: Call<Receipt>, t: Throwable) {
//            }
//
//            override fun onResponse(call: Call<Receipt>, response: Response<Receipt>) {
//                (receipt as MutableLiveData).postValue(response.body())
//            }
//        })
//    }
//}