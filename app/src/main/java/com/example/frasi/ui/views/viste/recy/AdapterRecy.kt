package com.example.frasi.ui.views.viste.recy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frasi.databinding.ItemListBinding
import com.example.frasi.ui.views.db.EntityFrase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdapterRecy(val context: Context, val onFraseClickedListener: OnFraseClickedListener) :
    RecyclerView.Adapter<AdapterRecy.ViewHolder>() {

    private var dataSet = ArrayList<EntityFrase>()


    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.binding.tTitolo.text = dataSet[position].titolo
        viewHolder.binding.tFrase.text = dataSet[position].frase
        viewHolder.binding.tAnno.text = dataSet[position].anno.toString()
        viewHolder.binding.tAutore.text = dataSet[position].autore


        viewHolder.binding.cardView.setOnClickListener {

            GlobalScope.launch {

                onFraseClickedListener.onClicked(dataSet[position])

            }
        }


        viewHolder.binding.cardView.setOnLongClickListener {

            GlobalScope.launch {
                onFraseClickedListener.onLongClicked(dataSet[position])

            }
            return@setOnLongClickListener true

        }
    }

    override fun getItemCount() = dataSet.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    fun setListData(data: ArrayList<EntityFrase>) {
        this.dataSet = data
    }




    interface OnFraseClickedListener {
        suspend fun onClicked(item: EntityFrase)
        suspend fun onLongClicked(item: EntityFrase)

    }


}



