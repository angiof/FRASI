package com.example.frasi.ui.recy

data class ModelData(val titolo: String, val frase: String, val anno: Int) {
    override fun toString(): String {
        return "(titolo='$titolo', frase='$frase', anno=$anno)"
    }
}
