package com.mironov.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class oneFragment : Fragment() {
    private val myAdapter = PhonesApdater()


    private fun loadData(){
        myAdapter.setupPhones(PhoneData.arr)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_one,container,false)
        val recycler = root.findViewById<RecyclerView>(R.id.recycler)

        loadData()

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = myAdapter
        return root
    }
}