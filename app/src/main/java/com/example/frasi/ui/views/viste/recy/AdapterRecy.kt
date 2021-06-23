package com.example.frasi.ui.views.viste.recy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frasi.databinding.ItemListBinding
import com.example.frasi.ui.views.db.EntityFrase

class AdapterRecy(context:Context) :
    RecyclerView.Adapter<AdapterRecy.ViewHolder>() {

     var dataSet= ArrayList<EntityFrase>()



    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.binding.tTitolo.text = dataSet[position].titolo
        viewHolder.binding.tFrase.text = dataSet[position].frase
        viewHolder.binding.tAnno.text=dataSet[position].anno.toString()
        viewHolder.binding.tAutore.text=dataSet[position].autore


    }

    override fun getItemCount() = dataSet.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }



    fun delate(i:Int){
        dataSet.removeAt(i)
        notifyDataSetChanged()
    }

    fun setListData(data: ArrayList<EntityFrase>) {
        this.dataSet = data
    }

}