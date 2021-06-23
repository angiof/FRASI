package com.example.frasi.ui.views.viste.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.frasi.databinding.FragmentHomeBinding
import com.example.frasi.ui.views.db.EntityFrase
import com.example.frasi.ui.views.viewmodels.FrasiViewModel
import com.example.frasi.ui.views.viste.recy.AdapterRecy
import com.example.frasi.ui.views.viste.recy.SwipeGestures

class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var adapterRecy: AdapterRecy
    private lateinit var viewModel: FrasiViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup ? ,
        savedInstanceState : Bundle ?
    ): View ? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        with(binding.recy) {

            setHasFixedSize(true)
            adapterRecy = AdapterRecy(requireContext())

            adapter = adapterRecy
            val swipe = object: SwipeGestures() {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when(direction) {
                        ItemTouchHelper.LEFT -> {
                            adapterRecy.delate(viewHolder.adapterPosition)
                            Toast.makeText(requireContext(), "mosso", Toast.LENGTH_LONG).show()
                        }
                    }
                    super.onSwiped(viewHolder, direction)
                }
            }
            val tocco = ItemTouchHelper(swipe)
            tocco.attachToRecyclerView(this)
        }

        viewModel =  ViewModelProvider(requireActivity()).get(FrasiViewModel::class.java)
        viewModel.frasi.observe(requireActivity(), {
            adapterRecy.setListData(ArrayList(it))
            adapterRecy.notifyDataSetChanged()
        })

        inserimento()


        return binding.root
    }

    fun inserimento(){

        viewModel.insert(EntityFrase(0,"dd","dd","ddd",11))
    }
}