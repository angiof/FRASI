package com.example.frasi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frasi.databinding.FragmentHomeBinding
import com.example.frasi.ui.recy.AdapterRecy
import com.example.frasi.ui.recy.ModelData

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding


    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val data: ArrayList<ModelData> = ArrayList()

       data.add(ModelData("xx "," xx ",1995))



        val adaptRacy =AdapterRecy(data,requireContext())

        binding= FragmentHomeBinding.inflate(layoutInflater)
        with(binding){

            recy.adapter=adaptRacy

        }
        return binding.root
    }
}