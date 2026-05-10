package com.example.proba.RecyclerView

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proba.R
import com.example.proba.toDisplayDate

class MyViewHolder(
    itemView: View,
    private val onItemClick: (Reserva) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val ivMaterial: ImageView = itemView.findViewById(R.id.ivMaterial)
    private val tvDescripcio: TextView = itemView.findViewById(R.id.tvDescripcio)
    private val tvDates: TextView = itemView.findViewById(R.id.tvDates)

    // Dins del teu MyViewHolder.kt
    fun bind(item: Reserva) {
        Glide.with(itemView.context).load(item.imatge).into(ivMaterial)
        tvDescripcio.text = item.descripcio

        val inici = item.datareserva.toDisplayDate()
        val fi = item.datafinal.toDisplayDate()

        tvDates.text = "$inici - $fi"

        itemView.setOnClickListener { onItemClick(item) }
    }
}