package com.example.frasi.ui.views.viste.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.example.frasi.R
import com.example.frasi.databinding.ButtonDialogsBinding
import com.example.frasi.databinding.FragmentHomeBinding
import com.example.frasi.ui.views.db.EntityFrase
import com.example.frasi.ui.views.viewmodels.FrasiViewModel
import com.example.frasi.ui.views.viste.recy.AdapterRecy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterRecy: AdapterRecy
    private lateinit var viewModel: FrasiViewModel
    private lateinit var buttonDialogsBinding: ButtonDialogsBinding
    lateinit var bindinBtnMod: ButtonDialogsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        adapterRecy = AdapterRecy(requireContext(), object : AdapterRecy.OnFraseClickedListener {


            override suspend fun onClicked(item: EntityFrase) {
                viewModel.delate(item)
            }

            override suspend fun onLongClicked(item: EntityFrase) {
                withContext(Dispatchers.Main) {
                    bindinBtnMod = ButtonDialogsBinding.inflate(layoutInflater)
                    val buttonMod =
                        MaterialDialog(requireContext(), BottomSheet()).show {
                            cornerRadius(16f)
                            customView(view = bindinBtnMod.root, scrollable = true)

                            title(R.string.titolo_modifica)

                            bindinBtnMod.tTitolo.setText(item.titolo)
                            bindinBtnMod.tAutore.setText(item.autore)
                            bindinBtnMod.tFrase.setText(item.frase)
                            bindinBtnMod.tAnno.setText(item.anno.toString())

                            //getter dei campi

                            positiveButton {
                                val id=item.id
                                val titolo:String=bindinBtnMod.tTitolo.text.toString()
                                val autore:String=bindinBtnMod.tAutore.text.toString()
                                val frase:String=bindinBtnMod.tFrase.text.toString()
                                val anno:Int=bindinBtnMod.tAnno.text.toString().toInt()
                                Log.d("au","$autore")

                                GlobalScope.launch {
                                    viewModel.update(EntityFrase(id,autore, titolo, frase, anno))
                                }
                            }
                            dismiss()
                            negativeButton {
                                dismiss()
                            }
                        }
                }
            }
        })
        viewModel = ViewModelProvider(requireActivity()).get(FrasiViewModel::class.java)


        val finderText=binding.finder

        finderText.addTextChangedListener {
            x->viewModel.aggiorna(x.toString())
        }




        viewModel.frasiTrack.observe(requireActivity(), {

            adapterRecy.setListData(ArrayList(it))
            adapterRecy.notifyDataSetChanged()
        })


        binding.floatingActionButton.setOnClickListener {
            buttonDialogsBinding = ButtonDialogsBinding.inflate(layoutInflater)
            val dialog = MaterialDialog(requireContext(), BottomSheet()).show {
                cornerRadius(16f)
                customView(view = buttonDialogsBinding.root, scrollable = true)
                positiveButton {

                    inserimento(
                        buttonDialogsBinding.tTitolo,
                        buttonDialogsBinding.tAutore,
                        buttonDialogsBinding.tFrase,
                        buttonDialogsBinding.tAnno
                    )

                }
                dismiss()
            }

        }
        with(binding.recy) {
            setHasFixedSize(true)
            adapter = adapterRecy
        }

        return binding.root

    }

    private fun inserimento(titolo: EditText, autore: EditText, frase: EditText, anno: EditText) {


        var eAutore: String = buttonDialogsBinding.tAutore.text.toString()
        var eTitolo: String = buttonDialogsBinding.tTitolo.text.toString()
        var eFrase: String = buttonDialogsBinding.tFrase.text.toString()
        var eAnno: Int = buttonDialogsBinding.tAnno.text.toString().toInt()

        viewModel.insert(EntityFrase(0, eAutore, eTitolo, eFrase, eAnno))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.aggiorna("")
    }



}