package com.pd.htn

import android.os.Bundle
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.environment.*

class EnvironmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.environment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        what_button.setOnClickListener{
            val layout = layoutInflater.inflate(R.layout.popup, null)
            val button = layout.findViewById<Button>(R.id.button_popup)

            val popup = PopupWindow(
                layout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            popup.elevation = 10.0F

            TransitionManager.beginDelayedTransition(environment)
            popup.showAtLocation(environment, Gravity.CENTER, 0, 0)
            button.setOnClickListener {
                popup.dismiss()
                layout.visibility = GONE
            }
        }

//        vm.customerAccounts.observe(this, Observer {
//            val chequeId = it.bankAccounts.find { it.type == "DDA" }?.id
//            val saveId = it.bankAccounts.find { it.type == "SDA" }?.id
//
//            if (chequeId != null && saveId != null) {
//                transferButton.setOnClickListener {
//                    vm.transferMoney(17.0, chequeId, saveId).observe(this, Observer {
//                        it.result?.let { response ->
//                            vm.tagReward(response.creditTransactionID)
//                        }
//                    })
//                }
//            }
//        })
    }
}