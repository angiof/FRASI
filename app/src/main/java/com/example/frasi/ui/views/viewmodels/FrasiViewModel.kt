package com.example.frasi.ui.views.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.frasi.ui.views.db.EntityFrase
import com.example.frasi.ui.views.repo.Repositorio
import kotlinx.coroutines.launch

class FrasiViewModel(application: Application) : AndroidViewModel(application) {

    private val respository: Repositorio = Repositorio(application)


    private var aggiorna=MutableLiveData<String>("")



    val frasiTrack:LiveData<List<EntityFrase>> =Transformations.switchMap(aggiorna){ string->
       respository.TrackFrasi(string)

    }

    val frasi: LiveData<List<EntityFrase>> = respository.paroleInOrdine

    val ordineAnno: LiveData<List<EntityFrase>> = respository.frasiPerAnno

    //in quale ambito lanciare questa curotines
    fun insert(frase: EntityFrase) = viewModelScope.launch {

        respository.insert(frase)

    }

    suspend fun delate(frase: EntityFrase) {

        respository.delete(frase)
    }

    suspend fun update(frase: EntityFrase) {
        respository.update(frase)
    }

    fun aggiorna(find:String){
        aggiorna.value=find

    }


}
