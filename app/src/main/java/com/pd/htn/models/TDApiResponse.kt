package com.pd.htn.models

open class TDApiResponse<T>(
    val errorMsg : String? = null,
    val result : T? = null,
    val requestId: String? = null,
    val statusCode : Double? = null
)