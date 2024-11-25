package com.mironov.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment


class RegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_registration, container, false)
        val navController = NavHostFragment.findNavController(this)
        val phoneNumberBtn = root.findViewById<Button>(R.id.firstBtn)
        val emailBtn = root.findViewById<Button>(R.id.secondBtn)
        val regBTN = root.findViewById<Button>(R.id.registrationBtn)

        val regEditText = root.findViewById<EditText>(R.id.firstEditText)
        val firstPasswordEditText = root.findViewById<EditText>(R.id.firstPassword)
        val secondPasswordEditText = root.findViewById<EditText>(R.id.secondPassword)

        val loginData = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)

        var isPhoneActive : Boolean = false

        //При инициализации экрана активна кнопка "по почте"
        emailBtn.setBackgroundColor(Color.parseColor("#FFBB86FC"))
        emailBtn.setTextColor(Color.parseColor("#000000"))
        regEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS


        phoneNumberBtn.setOnClickListener {
            phoneNumberBtn.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            phoneNumberBtn.setTextColor(Color.parseColor("#000000"))

            regEditText.inputType = InputType.TYPE_CLASS_PHONE
            emailBtn.setBackgroundColor(Color.parseColor("#651720"))
            emailBtn.setTextColor(Color.parseColor("#CA9760"))

            regEditText.setHint("Введите номер")
            isPhoneActive = true;

        }

        emailBtn.setOnClickListener {
            emailBtn.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            emailBtn.setTextColor(Color.parseColor("#000000"))

            regEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            phoneNumberBtn.setBackgroundColor(Color.parseColor("#651720"))
            phoneNumberBtn.setTextColor(Color.parseColor("#CA9760"))

            regEditText.setHint("Введите почту")
            isPhoneActive = false;
        }

        regBTN.setOnClickListener {
            var text = regEditText.text.toString();
            var firstPass = firstPasswordEditText.text.toString()
            var secondPass = secondPasswordEditText.text.toString()
            var isValid = true

            if (isPhoneActive) {
                if (!isPhoneNumberValid(text)) {
                    isValid = false
                }
            } else {
                if (!isEmailValid(text)) {
                    isValid = false
                }
            }

            if (!isPasswordValid(firstPass, secondPass)) {
                isValid = false
            }

            if (isValid) {
                Toast.makeText(requireActivity(), "Успешная регистрация", Toast.LENGTH_LONG).show()
                appendLoginData(isPhoneActive,loginData,text,firstPass)
                navController.navigate(R.id.loginFragment)            }

        }
        return root

    }

    private fun appendLoginData(isPhoneActive: Boolean, pref: SharedPreferences, data: String, password: String){
        if(isPhoneActive)
            pref.edit().putString("phoneNumber",data).apply()
        else
            pref.edit().putString("email",data).apply()

        pref.edit().putString("password", password).apply()
    }

    private fun isEmailValid(email: String): Boolean {
        if(!email.contains("@")){
            Toast.makeText(requireActivity(),"В почте нет @", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun isPhoneNumberValid(phone: String): Boolean{
        if(phone[0] != '+'){
            Toast.makeText(requireActivity(),"Некорректный номер телефона", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun isPasswordValid(firstPassword: String,confirmPassword : String) : Boolean{
        if(firstPassword.length < 8 ){
            Toast.makeText(requireActivity(),"Пароль слишком короткий", Toast.LENGTH_SHORT).show()
            return false
        }
        if(!firstPassword.equals(confirmPassword)){
            Toast.makeText(requireActivity(),"Пароли не совпадают", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }



}


