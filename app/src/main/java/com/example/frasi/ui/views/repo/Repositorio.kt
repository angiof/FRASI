package com.example.frasi.ui.views.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.frasi.ui.views.models.DaoFrasi
import com.example.frasi.ui.views.models.EntityFrase



class Repositorio(private val daoFrasi: DaoFrasi) {

    //rom esegue tutte le query in file tread separati
    //il livedata notifichera l-observer quando cambia

    val ParoleInOrdine: LiveData<List<EntityFrase>> = daoFrasi.ordineAlfa()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(entityFrase: EntityFrase) {
        daoFrasi.insert(entityFrase)
    }

}