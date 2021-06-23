package com.example.frasi.ui.views.viste.recy




data class ModelData(val titolo: String, val frase: String, val anno: Int=0,val autore:String="angelo ") {
    override fun toString(): String {
        return "(titolo='$titolo', frase='$frase', anno=$anno)"
    }
}
