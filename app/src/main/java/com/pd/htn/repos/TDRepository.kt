package com.pd.htn.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pd.htn.MainApplication
import com.pd.htn.models.*
import com.pd.htn.services.TDService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TDRepository {
    private val service = MainApplication.retrofit.create(TDService::class.java)

    fun getCustomer(id: String) : LiveData<Customer> {
        val customer : MutableLiveData<Customer> = MutableLiveData()

        service.getCustomer(id).enqueue(object :
            Callback<CustomerResponse> {
            override fun onFailure(call: Call<CustomerResponse>, t: Throwable) {
                customer.postValue(null)
            }

            override fun onResponse(
                call: Call<CustomerResponse>,
                response: Response<CustomerResponse>
            ) {
                customer.postValue(response.body()?.result)
            }
        })

        return customer
    }

    fun getCustomerTransactions(id: String) : LiveData<Collection<Transaction>> {
        val transactions : MutableLiveData<Collection<Transaction>> = MutableLiveData()

        service.getCustomerTransactions(id)
            .enqueue(object : Callback<TransactionResponse> {
                override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                    transactions.postValue(null)
                }

                override fun onResponse(
                    call: Call<TransactionResponse>,
                    response: Response<TransactionResponse>
                ) {
                    transactions.postValue(response.body()?.result)
                }
            })

        return transactions
    }

    fun transferMoney(transfer: Transfer) : LiveData<TDApiResponse<Unit>> {
        val postResponse : MutableLiveData<TDApiResponse<Unit>> = MutableLiveData()

        service.transferMoney(transfer)
            .enqueue(object : Callback<TDApiResponse<Unit>> {
                override fun onFailure(call: Call<TDApiResponse<Unit>>, t: Throwable) {
                    postResponse.postValue(null)
                }

                override fun onResponse(
                    call: Call<TDApiResponse<Unit>>,
                    response: Response<TDApiResponse<Unit>>
                ) {
                    postResponse.postValue(response.body())
                }
            })

        return postResponse
    }
}