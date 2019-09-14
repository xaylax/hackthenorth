package com.pd.htn.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pd.htn.BuildConfig
import com.pd.htn.models.Transaction
import com.pd.htn.repos.TDRepository

class FinancialViewModel : ViewModel() {
    private val repo = TDRepository()

    private val transactions = repo.getCustomerTransactions(BuildConfig.USER_ID)
    val utilTransactions : LiveData<Collection<Transaction>> = Transformations.map(transactions) {
        it.filter { transaction -> transaction.merchantCode == 4900 }
    }

    val transitTransactions : LiveData<Collection<Transaction>> = Transformations.map(transactions) {
        it.filter { transaction -> transaction.merchantCode == 5814 }
    }

    val gasTransactions : LiveData<Collection<Transaction>> = Transformations.map(transactions) {
        it.filter { transaction -> transaction.merchantCode == 5541 }
    }

    val parkingTransactions : LiveData<Collection<Transaction>> = Transformations.map(transactions) {
        it.filter { transaction -> transaction.merchantCode == 7523 }
    }
}