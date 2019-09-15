package com.pd.htn

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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pd.htn.vm.EnvironmentViewModel
import kotlinx.android.synthetic.main.environment.*

class EnvironmentFragment : Fragment() {

    private lateinit var vm : EnvironmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm = ViewModelProvider(activity as AppCompatActivity).get(EnvironmentViewModel::class.java)


//        val howButton: Button = view!!.findViewById(R.id.how_button)
//        howButton.setOnClickListener{
//            val popup = PopupWindow(view,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//            val textView: TextView = view!!.findViewById(R.id.what_carbon_footprint)
//            textView.visibility = View.VISIBLE
//            val buttonPopUp = view!!.findViewById<Button>(R.id.how_button)
//            buttonPopUp.setOnClickListener{
//                popup.dismiss()
//            }
//
//            TransitionManager.beginDelayedTransition(environment)
//            popup.showAtLocation(environment,Gravity.CENTER,0,0)
//        }
        return inflater.inflate(R.layout.environment, container, false)


    }
}