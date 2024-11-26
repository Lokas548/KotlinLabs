package com.mironov.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment


class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_splash, container, false);
        val navController = NavHostFragment.findNavController(this)
        val loginData = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)



        if(isRegistered(loginData)){
            if(isChecked(loginData)){
                navController.navigate(R.id.firstFragment)
            }
            else
                navController.navigate(R.id.loginFragment)
        }
        else{
            navController.navigate(R.id.registrationFragment)
        }
        return root
    }

    private fun isRegistered(data: SharedPreferences): Boolean {
        return data.getBoolean("isRegistered", false);
    }

    private fun isChecked(data: SharedPreferences): Boolean{
        return data.getBoolean("isAutoAuthActive", false)
    }

}


