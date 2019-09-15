package com.pd.htn.models

data class Transfer(
    val amount: Double,
    val fromAccountId : String,
    val receipt : String, // free form JSON
    val toAccountId : String,
    val currency : String = "CAD"
)

data class TransferPOSTResponse(
    val creditTransactionID : String // which ever account got the "CREDIT"
)

