package com.mironov.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth)

        val inputText = findViewById<EditText>(R.id.inputText)
        val authBtn = findViewById<Button>(R.id.authBtn)
        val password = findViewById<EditText>(R.id.password)
        val checkBox = findViewById<CheckBox>(R.id.checkbox)

        val loginData = getSharedPreferences("data", Context.MODE_PRIVATE)

        authBtn.setOnClickListener {
            if (isLoginDataEqual(inputText.text.toString(), password.text.toString(), loginData)) {
                loginData.edit().putBoolean("isAutoAuthActive", checkBox.isChecked).apply()
                startActivity(Intent(this, ContentActivity::class.java))
            } else {
                Toast.makeText(this, "Некорректные данные", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun isLoginDataEqual(login: String, password: String, loginData: SharedPreferences): Boolean {
        if ((login.equals(loginData.getString("email", "default")) ||
            login.equals(loginData.getString("phoneNumber", "default"))) &&
            password.equals(loginData.getString("password", "default"))
        ) {
            return true
        }
        return false
    }

}