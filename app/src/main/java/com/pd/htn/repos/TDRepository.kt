package com.pd.htn.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pd.htn.MainApplication
import com.pd.htn.models.*
import com.pd.htn.services.TDService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

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

    fun getCustomerAccounts(id: String) : LiveData<Accounts> {
        val getResponse : MutableLiveData<Accounts> = MutableLiveData()

        service.getCustomerAccounts(id).enqueue(object : Callback<AccountsResponse> {
            override fun onFailure(call: Call<AccountsResponse>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(
                call: Call<AccountsResponse>,
                response: Response<AccountsResponse>
            ) {
                getResponse.postValue(response.body()?.result)
            }
        })

        return getResponse
    }

    fun getCustomerTransactions(id: String) : LiveData<Collection<Transaction>> {
        val transactions : MutableLiveData<Collection<Transaction>> = MutableLiveData()

        service.getCustomerTransactions(id)
            .enqueue(object : Callback<TransactionResponse> {
                override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                    Timber.e(t)
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

    fun transferMoney(transfer: Transfer) : LiveData<TDApiResponse<TransferPOSTResponse>> {
        val postResponse : MutableLiveData<TDApiResponse<TransferPOSTResponse>> = MutableLiveData()

        service.transferMoney(transfer)
            .enqueue(object : Callback<TDApiResponse<TransferPOSTResponse>> {
                override fun onFailure(call: Call<TDApiResponse<TransferPOSTResponse>>, t: Throwable) {
                    postResponse.postValue(null)
                }

                override fun onResponse(
                    call: Call<TDApiResponse<TransferPOSTResponse>>,
                    response: Response<TDApiResponse<TransferPOSTResponse>>
                ) {
                    postResponse.postValue(response.body())
                }
            })

        return postResponse
    }

    fun assignRewardTag(transactionId: String) {
        service.changeCategoryTags(transactionId, listOf("Reward")).enqueue(
            object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Timber.e(t)
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Timber.e(response.body()?.toString()) // Assumed it will succeed
                }
            })
    }
}