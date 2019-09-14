package com.pd.htn.services

import com.pd.htn.models.CustomerResponse
import com.pd.htn.models.TDApiResponse
import com.pd.htn.models.TransactionResponse
import com.pd.htn.models.Transfer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TDService {
    @GET("customers/{custId}")
    fun getCustomer(@Path("custId") customerId: String) : Call<CustomerResponse>

    @GET("customers/{custId}/transactions")
    fun getCustomerTransactions(@Path("custId") customerId: String) : Call<TransactionResponse>

    @POST("transfers")
    fun transferMoney(@Body transfer : Transfer) : Call<TDApiResponse<Unit>>
}