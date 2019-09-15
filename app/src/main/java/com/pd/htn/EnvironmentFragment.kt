package com.pd.htn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pd.htn.vm.EnvironmentViewModel

class EnvironmentFragment : Fragment() {

    private lateinit var vm : EnvironmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm = ViewModelProvider(activity as AppCompatActivity).get(EnvironmentViewModel::class.java)

        return inflater.inflate(R.layout.environment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.customerAccounts.observe(this, Observer {
            val chequeId = it.bankAccounts.find { it.type == "DDA" }?.id
            val saveId = it.bankAccounts.find { it.type == "SDA" }?.id

            if (chequeId != null && saveId != null) {
                vm.transferMoney(17.0, chequeId, saveId).observe(this, Observer {
                    it.result?.let { response ->
                        vm.tagReward(response.creditTransactionID)
                    }
                })
            }
        })
    }
}