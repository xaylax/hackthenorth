package com.pd.htn.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pd.htn.BuildConfig
import com.pd.htn.models.*
import com.pd.htn.repos.TDRepository

class EnvironmentViewModel : ViewModel() {
    private val repo = TDRepository()
    val customerAccounts : LiveData<Accounts> = repo.getCustomerAccounts(BuildConfig.USER_ID)
    private val transactions : LiveData<Collection<Transaction>> = repo.getCustomerTransactions(BuildConfig.USER_ID)


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

    fun transferMoney(amount: Double, chequeId: String, saveId: String) : LiveData<TDApiResponse<TransferPOSTResponse>> {
        val transfer = Transfer(amount, chequeId, "", saveId)

        return repo.transferMoney(transfer)
    }

    fun tagReward(transactionId: String) = repo.assignRewardTag(transactionId)
}