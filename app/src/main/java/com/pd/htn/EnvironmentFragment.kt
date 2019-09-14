package com.pd.htn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
}