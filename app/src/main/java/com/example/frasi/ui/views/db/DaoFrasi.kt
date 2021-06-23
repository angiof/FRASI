package com.example.frasi.ui.views.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoFrasi {


    @Query("select * from  frasi order by autore asc")
     fun ordineAlfa(): LiveData<List<EntityFrase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityFrase: EntityFrase)

    @Query("select * from  frasi order by anno asc")
    fun ordineAnno(): LiveData<List<EntityFrase>>



    @Query("delete from frasi")
    suspend fun cancella()


}