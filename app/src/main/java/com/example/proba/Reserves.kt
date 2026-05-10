package com.example.proba

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proba.RecyclerView.MyAdapter
import com.example.proba.ViewModel.ViewModel

class Reserves : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    // Inicialización correcta del ViewModel en Activity
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserves)

        recyclerView = findViewById(R.id.rvReserves)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // En tu onCreate de Reserves.kt
        adapter = MyAdapter(
            items = emptyList(),
            onItemClick = { reserva -> /* Lo que hagas al clicar la fila */ },
        )
        recyclerView.adapter = adapter

        viewModel.reserva.observe(this) { listaReserva ->
            adapter.updateList(listaReserva)
        }

        val idUsuari = intent.getIntExtra("USUARI_ID", -1)

        // El ViewModel de esta pantalla se encarga de buscar las reservas
        if (idUsuari != -1) {
            viewModel.cargarReserva(idUsuari)
        }

  }
}