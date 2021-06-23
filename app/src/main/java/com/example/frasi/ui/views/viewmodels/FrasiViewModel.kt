package com.example.frasi.ui.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frasi.ui.views.models.EntityFrase
import com.example.frasi.ui.views.repo.Repositorio
import kotlinx.coroutines.launch

class FrasiViewModel(private val respository: Repositorio) : ViewModel() {

    val frasi: LiveData<List<EntityFrase>> = respository.ParoleInOrdine


    fun insert(frase: EntityFrase)=viewModelScope.launch {

        respository.insert(frase)

    }



}