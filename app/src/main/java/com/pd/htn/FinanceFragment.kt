package com.pd.htn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pd.htn.vm.FinancialViewModel
import kotlinx.android.synthetic.main.finance.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        totalCard.setOnClickListener {
            arrow.rotation = if (rewardHistory.isVisible) 180f else 0f
            rewardHistory.isVisible = !rewardHistory.isVisible
        }

        vm.totalRewardTransactions.observe(this, Observer {
            val doubleString = it.toString().split(".")

            ones.text = doubleString[0]
            decimal.text = ".${doubleString[1]}"
        })
    }
}