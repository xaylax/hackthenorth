package com.pd.htn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pd.htn.vm.FinancialViewModel

class FinanceFragment : Fragment() {

    private lateinit var vm : FinancialViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm = ViewModelProvider(this).get(FinancialViewModel::class.java)
        return inflater.inflate(R.layout.finance, container, false)
    }
}