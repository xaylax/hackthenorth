package com.pd.htn.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pd.htn.BuildConfig
import com.pd.htn.repos.TDRepository

class UserViewModel : ViewModel() {
    private val repo = TDRepository()

    val name : LiveData<String> = Transformations.map(repo.getCustomer(BuildConfig.USER_ID)) {
        "${it.givenName} ${it.surname}"
    }
}