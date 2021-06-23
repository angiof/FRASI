package com.example.frasi.ui.views.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoFrasi {


    @Query("select * from  frasi order by autore asc")
     fun ordineAlfa(): LiveData<List<EntityFrase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityFrase: EntityFrase)

    @Query("delete from frasi")
    suspend fun cancella()


}