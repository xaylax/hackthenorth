package com.pd.htn.services

import com.pd.htn.models.*
import retrofit2.Call
import retrofit2.http.*

interface TDService {
    @GET("customers/{custId}")
    fun getCustomer(@Path("custId") customerId: String) : Call<CustomerResponse>

    @GET("customers/{custId}/transactions")
    fun getCustomerTransactions(@Path("custId") customerId: String) : Call<TransactionResponse>

    @GET("customers/{custId}/accounts")
    fun getCustomerAccounts(@Path("custId") customerId: String) : Call<AccountsResponse>

    @POST("transfers")
    fun transferMoney(@Body transfer : Transfer) : Call<TDApiResponse<TransferPOSTResponse>>

//    @Headers( { "Content-Type: application/octet-stream",
//        "Ocp-Apim-Subscription-Key: e26e9d900bcb477ebb72985ededb6215"
//    })
//    @POST("")
//    fun uploadReceipt(@Body image: ByteArray) : Call<Receipt>

    @PUT("transactions/{transId}/tags")
    fun changeCategoryTags(@Path("transId") transactionId : String, @Body tags : Collection<String>) : Call<Unit>
}