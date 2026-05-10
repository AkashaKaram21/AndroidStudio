package com.example.proba

import java.text.SimpleDateFormat
import java.util.Locale

// Extension function: ara qualsevol String té el mètode .toDisplayDate()
fun String.toDisplayDate(): String {
    // El format que ve de l'API (només la part de la data)
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    // El format que volem mostrar
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    
    return try {
        // Agafem només els primers 10 caràcters (per si ve amb l'hora 'T00:00:00')
        val dateStr = this.take(10)
        val date = inputFormat.parse(dateStr)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        this // Si falla, retorna el text original
    }
}