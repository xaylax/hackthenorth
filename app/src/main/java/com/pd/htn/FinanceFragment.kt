package com.pd.htn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        mutual_funds.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.td.com/ca/en/personal-banking/products/saving-investing/mutual-funds/td-mutual-funds/")))
        }

        comfort_portfolio.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.td.com/ca/en/personal-banking/products/saving-investing/mutual-funds/td-comfort-portfolios/")))
        }

        hisa.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.td.com/ca/en/personal-banking/products/bank-accounts/savings-accounts/high-interest-savings-account/")))
        }



        vm.rewardTransactions.observe(this, Observer {
            (rewardHistory as RecyclerView).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = RecyclerAdapter(it)
            }
        })

        vm.totalRewardTransactions.observe(this, Observer {
            val doubleString = it.toString().split(".")

            ones.text = doubleString[0]
            decimal.text = ".${doubleString[1]}"
        })
    }
}