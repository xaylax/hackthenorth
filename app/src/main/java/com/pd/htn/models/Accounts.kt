package com.pd.htn.models

data class Account(
    val id: String,
    val type: String,
    val balance: Double
)

data class Accounts (
    val bankAccounts : Collection<Account>,
    val creditCardAccounts : Collection<Account>
)

class AccountsResponse : TDApiResponse<Accounts>()