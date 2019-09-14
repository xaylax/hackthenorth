package com.pd.htn.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pd.htn.models.TDApiResponse
import com.pd.htn.models.Transfer
import com.pd.htn.repos.TDRepository

class EnvironmentViewModel : ViewModel() {
    private val repo = TDRepository()

    fun transferMoney(transfer : Transfer) : LiveData<TDApiResponse<Unit>> = repo.transferMoney(transfer)
}