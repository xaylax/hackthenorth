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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pd.htn.vm.EnvironmentViewModel
import kotlinx.android.synthetic.main.environment.*
import kotlinx.android.synthetic.main.popup_what_footprint.*

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
            val layout = layoutInflater.inflate(R.layout.popup_what_footprint, null)
            val button = layout.findViewById<Button>(R.id.button_popup)
            val popupText = layout.findViewById<TextView>(R.id.popup_text)


            val popup = PopupWindow(
                layout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            popup.elevation = 10.0F
            popupText.visibility=View.VISIBLE

            TransitionManager.beginDelayedTransition(environment)
            popup.showAtLocation(environment, Gravity.CENTER, 0, 0)
            button.setOnClickListener {
                popup.dismiss()
                layout.visibility = GONE
            }
        }

        how_button.setOnClickListener{
            val layout = layoutInflater.inflate(R.layout.popup_calculate_footprint, null)
            val button = layout.findViewById<Button>(R.id.button_popup)
            val popupText = layout.findViewById<TextView>(R.id.popup_text)

            val popup = PopupWindow(
                layout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            popup.elevation = 10.0F
            popupText.visibility=View.VISIBLE

            TransitionManager.beginDelayedTransition(environment)
            popup.showAtLocation(environment, Gravity.CENTER, 0, 0)
            button.setOnClickListener {
                popup.dismiss()
                layout.visibility = GONE
            }
        }

        consumption_button.setOnClickListener{
            val layout = layoutInflater.inflate(R.layout.popup_reduce_consumption, null)
            val button = layout.findViewById<Button>(R.id.button_popup)
            val popupText = layout.findViewById<TextView>(R.id.popup_text)

            val popup = PopupWindow(
                layout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            popup.elevation = 10.0F
            popupText.visibility=View.VISIBLE

            TransitionManager.beginDelayedTransition(environment)
            popup.showAtLocation(environment, Gravity.CENTER, 0, 0)
            button.setOnClickListener {
                popup.dismiss()
                layout.visibility = GONE
            }
        }

        produce_button.setOnClickListener{
            val layout = layoutInflater.inflate(R.layout.popup_right_produce, null)
            val button = layout.findViewById<Button>(R.id.button_popup)
            val popupText = layout.findViewById<TextView>(R.id.popup_text)

            val popup = PopupWindow(
                layout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            popup.elevation = 10.0F
            popupText.visibility=View.VISIBLE

            TransitionManager.beginDelayedTransition(environment)
            popup.showAtLocation(environment, Gravity.CENTER, 0, 0)
            button.setOnClickListener {
                popup.dismiss()
                layout.visibility = GONE
            }
        }

        gain_button.setOnClickListener{
            val layout = layoutInflater.inflate(R.layout.popup_gain, null)
            val button = layout.findViewById<Button>(R.id.button_popup)
            val popupText = layout.findViewById<TextView>(R.id.popup_text)

            val popup = PopupWindow(
                layout,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            popup.elevation = 10.0F
            popupText.visibility=View.VISIBLE

            TransitionManager.beginDelayedTransition(environment)
            popup.showAtLocation(environment, Gravity.CENTER, 0, 0)
            button.setOnClickListener {
                popup.dismiss()
                layout.visibility = GONE
            }
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