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

    val rewardTransactions : LiveData<Collection<Transaction>> = Transformations.map(transactions) {
        it.filter { transaction -> transaction.categoryTags.contains("Reward") }
    }

    val totalRewardTransactions : LiveData<Double> = Transformations.map(rewardTransactions) {
        it.sumByDouble { it.currencyAmount }
    }
}