package com.pd.htn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class EnvironmentActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.environment)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.nav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.environment_nav -> {
                    val environmentActivity =
                        Intent(applicationContext, EnvironmentActivity::class.java)
                    startActivity(environmentActivity)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home_nav -> {
                    val mainActivity = Intent(applicationContext, MainActivity::class.java)
                    startActivity(mainActivity)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.finance_nav -> {
                    val financeActivity = Intent(applicationContext, FinanceActivity::class.java)
                    startActivity(financeActivity)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}