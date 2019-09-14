package com.pd.htn.models

data class Customer(
    val givenName : String,
    val surname : String,
    val age : Int
)

class CustomerResponse : TDApiResponse<Customer>()