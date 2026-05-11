package com.example.crud.RecyclerView

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crud.R


class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val ivMaterial: ImageView = view.findViewById(R.id.ivMaterial)
    private val tvDescripcio: TextView = view.findViewById(R.id.tvDescripcio)
    private val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)

    fun bind(item: Material, onItemClick: (Material) -> Unit, onDeleteClick: (Int) -> Unit) {
        tvDescripcio.text = item.descripcio

        // Carrega la imatge amb Glide [4]
        Glide.with(itemView.context)
            .load(item.imatge)
            .into(ivMaterial)

        itemView.setOnClickListener { onItemClick(item) }
        btnDelete.setOnClickListener { onDeleteClick(item.id) }
    }
}