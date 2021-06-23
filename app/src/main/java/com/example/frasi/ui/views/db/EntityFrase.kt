package com.example.frasi.ui.views.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "frasi")
data class EntityFrase
    (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "autore") val autore: String,
    @ColumnInfo(name = "titolo") val titolo: String,
    @ColumnInfo(name = "frase") val frase: String,
    @ColumnInfo(name = "anno") val anno: Int
)

