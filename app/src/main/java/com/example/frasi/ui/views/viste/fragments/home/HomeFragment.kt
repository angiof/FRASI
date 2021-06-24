package com.example.frasi.ui.views.viste.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.example.frasi.databinding.ButtonDialogsBinding
import com.example.frasi.databinding.FragmentHomeBinding
import com.example.frasi.ui.views.db.EntityFrase
import com.example.frasi.ui.views.viewmodels.FrasiViewModel
import com.example.frasi.ui.views.viste.recy.AdapterRecy
import com.example.frasi.ui.views.viste.recy.SwipeGestures

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var adapterRecy: AdapterRecy
    private lateinit var viewModel: FrasiViewModel
    lateinit var buttonDialogsBinding: ButtonDialogsBinding

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(requireActivity()).get(FrasiViewModel::class.java)
        viewModel.frasi.observe(requireActivity(), {
            adapterRecy.setListData(ArrayList(it))
            adapterRecy.notifyDataSetChanged()
        })
        binding.floatingActionButton.setOnClickListener {
            buttonDialogsBinding = ButtonDialogsBinding.inflate(layoutInflater)

            val dialog = MaterialDialog(requireContext(), BottomSheet()).show {
                cornerRadius(16f)
                customView(view = buttonDialogsBinding.root, scrollable = true)


                positiveButton {

                    inserimento(buttonDialogsBinding.tTitolo,buttonDialogsBinding.tAutore,buttonDialogsBinding.tFrase,buttonDialogsBinding.tAnno)

                }
                dismiss()

            }

        }

        with(binding.recy) {
            setHasFixedSize(true)
            adapterRecy = AdapterRecy(requireContext())
            adapter = adapterRecy

            val swipe = object : SwipeGestures() {
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
            val tocco = ItemTouchHelper(swipe)
            tocco.attachToRecyclerView(this)

        }
        return binding.root
    }

    private fun inserimento(titolo:EditText, autore: EditText,frase:EditText,anno:EditText) {


        var eAutore:String=buttonDialogsBinding.tAutore.text.toString()
        var eTitolo:String=buttonDialogsBinding.tTitolo.text.toString()
        var eFrase:String=buttonDialogsBinding.tFrase.text.toString()
        var eAnno: Int =buttonDialogsBinding.tAnno.text.toString().toInt()



        viewModel.insert(EntityFrase(0,eAutore,eTitolo,eFrase,eAnno))





    }



}