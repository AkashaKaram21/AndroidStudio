package com.example.crud.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.R

class MyAdapter(
    private var items: List<Material>,
    private val onItemClick: (Material) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_material, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position], onItemClick, onDeleteClick)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Material>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    fun updateList(lista: List<Material>) {
        items = lista
        notifyDataSetChanged()
    }
}