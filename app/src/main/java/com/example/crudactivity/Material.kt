package com.example.crudactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.RecyclerView.MyAdapter
import com.example.crud.ViewModel.ViewModel

class Material : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        // 1. Buscamos el RecyclerView
        val rvMaterial = findViewById<RecyclerView>(R.id.rvMaterials)
       // val btnCreate = findViewById<Button>(R.id.btnCreate)

        // 2. Configuramos el Adapter
        adapter = MyAdapter(
            emptyList(),
            onItemClick = { material ->
                // Ir a detalles o editar
                val intent = Intent(this, MaterialCreate::class.java)
                intent.putExtra("material_id", material.id)
                startActivity(intent)
            },
            onDeleteClick = { id ->
                viewModel.deleteMaterial(id)
                Toast.makeText(this, "Eliminando...", Toast.LENGTH_SHORT).show()
            }
        )

        // 3. Configuramos el RecyclerView
        rvMaterial.layoutManager = LinearLayoutManager(this)
        rvMaterial.adapter = adapter

        // 4. Observar la lista de materiales
        viewModel.materials.observe(this) { lista ->
            adapter.updateList(lista)
        }

        // 5. Botón para crear nuevo material
//        btnCreate.setOnClickListener {
//            startActivity(Intent(this, MaterialCreate::class.java))
//        }

        // 6. Cargar los datos
        viewModel.fetchMaterials()
    }
}