package com.mironov.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val loginData = getSharedPreferences("data", Context.MODE_PRIVATE)

        if(isNotNull(loginData)){
            if(isChecked(loginData)){
                startActivity(Intent(this,ContentActivity::class.java))
            }
            else
                startActivity(Intent(this,LoginActivity::class.java))
        }
        else{
            startActivity(Intent(this,RegistrationActivity::class.java))
        }

    }

    private fun isNotNull(data: SharedPreferences): Boolean {
        return data.contains("email") && data.contains("password") ||
                data.contains("phoneNumber") && data.contains("password")
    }

    private fun isChecked(data: SharedPreferences): Boolean{
        return data.getBoolean("isAutoAuthActive",false)
    }


}