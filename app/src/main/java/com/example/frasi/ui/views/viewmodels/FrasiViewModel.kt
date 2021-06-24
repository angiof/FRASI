package com.example.frasi.ui.views.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frasi.ui.views.db.DaoFrasi
import com.example.frasi.ui.views.db.EntityFrase
import com.example.frasi.ui.views.repo.Repositorio
import kotlinx.coroutines.launch

class FrasiViewModel (application: Application) : AndroidViewModel(application){

    private val respository: Repositorio=Repositorio(application)


    val frasi: LiveData<List<EntityFrase>> = respository.paroleInOrdine

    val ordineAnno: LiveData<List<EntityFrase>> = respository.frasiPerAnno


    //in quale ambito lanciare questa curotines
    fun insert(frase: EntityFrase) = viewModelScope.launch {

        respository.insert(frase)

    }


}
