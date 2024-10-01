package com.mironov.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val phoneNumberBtn = findViewById<Button>(R.id.firstBtn)
        val emailBtn = findViewById<Button>(R.id.secondBtn)
        val regBTN = findViewById<Button>(R.id.registrationBtn)

        val regEditText = findViewById<EditText>(R.id.firstEditText)
        val firstPasswordEditText = findViewById<EditText>(R.id.firstPassword)
        val secondPasswordEditText = findViewById<EditText>(R.id.secondPassword)

        var isPhoneActive : Boolean = false

        //При инициализации экрана активна кнопка "по почте"
        emailBtn.setBackgroundColor(Color.parseColor("#FFBB86FC"))
        emailBtn.setTextColor(Color.parseColor("#000000"))

        phoneNumberBtn.setOnClickListener {
            phoneNumberBtn.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            phoneNumberBtn.setTextColor(Color.parseColor("#000000"))

            emailBtn.setBackgroundColor(Color.parseColor("#651720"))
            emailBtn.setTextColor(Color.parseColor("#CA9760"))

            regEditText.setHint("Введите номер")
            isPhoneActive = true;

        }

        emailBtn.setOnClickListener {
            emailBtn.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            emailBtn.setTextColor(Color.parseColor("#000000"))

            phoneNumberBtn.setBackgroundColor(Color.parseColor("#651720"))
            phoneNumberBtn.setTextColor(Color.parseColor("#CA9760"))

            regEditText.setHint("Введите почту")
            isPhoneActive = false;
        }

        regBTN.setOnClickListener {
            var text = regEditText.text.toString();
            var firstPass = firstPasswordEditText.text.toString()
            var secondPass = secondPasswordEditText.text.toString()


            if(isPhoneActive) {
                isPhoneNumberValid(text)
                isPasswordValid(firstPass,secondPass)
            }
            else {
                isEmailValid(text)
                isPasswordValid(firstPass,secondPass)
            }

        }
    }

    fun isEmailValid(email: String){
        if(!email.contains("@")){
            Toast.makeText(this,"В почте нет @",Toast.LENGTH_LONG).show()
        }
    }

    fun isPhoneNumberValid(phone: String){
        if(phone[0] != '+'){
            Toast.makeText(this,"Некорректный номер телефона",Toast.LENGTH_LONG).show()
        }
    }

    fun isPasswordValid(firstPassword: String,confirmPassword : String){
        if(firstPassword.length < 8 ){
            Toast.makeText(this,"Пароль слишком короткий",Toast.LENGTH_LONG).show()
        }
        if(!firstPassword.equals(confirmPassword)){
            Toast.makeText(this,"Пароли не совпадают",Toast.LENGTH_LONG).show()
        }
    }
}