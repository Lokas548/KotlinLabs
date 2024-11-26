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
import com.google.firebase.auth.FirebaseAuth


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
            if (inputText.text.isNotEmpty() && password.text.isNotEmpty()) {
                auth.signInWithEmailAndPassword(inputText.text.toString(), password.text.toString())
                    .addOnCompleteListener { ans ->
                        if(ans.isSuccessful) {
                            loginData.edit().putBoolean("isAutoAuthActive", checkBox.isChecked)
                                .apply()
                            navController.navigate(R.id.firstFragment)
                        }
                    }.addOnFailureListener { exception ->
                            Toast.makeText(requireContext(), "Некорректные данные", Toast.LENGTH_SHORT).show()
                    }
            }
            else {
                Toast.makeText(requireContext(), "Поля пустые", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }

}