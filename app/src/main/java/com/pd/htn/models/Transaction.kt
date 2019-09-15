package com.pd.htn.models

import java.util.*

data class Transaction(
    val currencyAmount: Double,
    val originationDateTime: Date,
    val merchantCode: Int,
    val categoryTags: Collection<String>
)

class TransactionResponse : TDApiResponse<Collection<Transaction>>()