package com.example.frasi.ui.views.repo

import android.app.Application
import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.frasi.ui.views.db.DaoFrasi
import com.example.frasi.ui.views.db.DbFrasi
import com.example.frasi.ui.views.db.EntityFrase


class Repositorio(context: Context) {

    private var daoFrasi: DaoFrasi= DbFrasi.getDatabase(context).wordDao()

    //rom esegue tutte le query in file tread separati
    //il livedata notifichera l-observer quando cambia

    val paroleInOrdine: LiveData<List<EntityFrase>> = daoFrasi.ordineAlfa()

    val frasiPerAnno:LiveData<List<EntityFrase>> =daoFrasi.ordineAnno()






    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(entityFrase: EntityFrase) {
        daoFrasi.insert(entityFrase)
    }




}