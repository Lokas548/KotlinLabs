package com.mironov.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val navController = NavHostFragment.findNavController(this)

        val inputText = root.findViewById<EditText>(R.id.inputText)
        val authBtn = root.findViewById<Button>(R.id.authBtn)
        val password = root.findViewById<EditText>(R.id.password)
        val checkBox = root.findViewById<CheckBox>(R.id.checkbox)

        val loginData = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)

        authBtn.setOnClickListener {
            if (isLoginDataEqual(inputText.text.toString(), password.text.toString(), loginData)) {
                loginData.edit().putBoolean("isAutoAuthActive", checkBox.isChecked).apply()
                navController.navigate(R.id.firstFragment)
            } else {
                Toast.makeText(requireContext(), "Некорректные данные", Toast.LENGTH_SHORT).show()
            }
        }
        return root
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