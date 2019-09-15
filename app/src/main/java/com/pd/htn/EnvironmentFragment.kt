package com.pd.htn

import android.content.Context
import android.os.Bundle
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Slide
import com.pd.htn.vm.EnvironmentViewModel
import kotlinx.android.synthetic.main.environment.*
import kotlinx.android.synthetic.main.popup.*

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

       what_button.setOnClickListener{
            val popup = PopupWindow(layoutInflater.inflate(R.layout.popup, null),
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
           popup.elevation = 10.0F

           button_popup.setOnClickListener{
               popup.dismiss()
           }
           TransitionManager.beginDelayedTransition(environment)
           popup.showAtLocation(environment, Gravity.CENTER, 0, 0)
       }

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