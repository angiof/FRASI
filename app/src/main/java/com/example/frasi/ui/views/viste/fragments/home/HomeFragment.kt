package com.example.frasi.ui.views.viste.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.frasi.databinding.FragmentHomeBinding
import com.example.frasi.ui.views.viste.recy.AdapterRecy
import com.example.frasi.ui.views.viste.recy.ModelData
import com.example.frasi.ui.views.viste.recy.SwipeGestures

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var adapterRecy: AdapterRecy

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





        val data: ArrayList<ModelData> = ArrayList()

        data.add(ModelData("le query", " una relazione n-n ti puo cambiare la vita  ", 2021))
        data.add(ModelData("le query", " una relazione n-n ti puo cambiare la vita  ", 2021))
        data.add(ModelData("le query", " una relazione n-n ti puo cambiare la vita  ", 2021))
        data.add(ModelData("le query", " una relazione n-n ti puo cambiare la vita  ", 2021, "angelo")
        )

        binding = FragmentHomeBinding.inflate(layoutInflater)
        with(binding) {

             adapterRecy = AdapterRecy(data, requireContext())

            recy.adapter = adapterRecy


            val swipe= object : SwipeGestures() {

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            adapterRecy.delate(viewHolder.adapterPosition)
                            Toast.makeText(requireContext(), "mosso", Toast.LENGTH_LONG).show()
                        }
                    }
                    super.onSwiped(viewHolder, direction)
                }
            }
            val tocco=ItemTouchHelper(swipe)
            tocco.attachToRecyclerView(recy)


        }
        return binding.root
    }
}