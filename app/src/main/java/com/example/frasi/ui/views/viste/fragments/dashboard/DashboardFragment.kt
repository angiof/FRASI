package com.example.frasi.ui.views.viste.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.frasi.R
import com.example.frasi.databinding.FragmentDashboardBinding
import com.example.frasi.ui.views.viste.fragments.home.HomeFragment

class DashboardFragment : Fragment() {
    val args : DashboardFragmentArgs by navArgs()
    private var binding: FragmentDashboardBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentDashboardBinding.inflate(layoutInflater)

        with(binding){

            this!!.tReciver.text=args.myArg.toString()
            this!!.tReciver2.text=args.titoloFromHome.toString()
        }


        return binding!!.root
    }


}