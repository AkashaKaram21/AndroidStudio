package com.example.crudactivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud.RecyclerView.Material
import com.example.crud.ViewModel.ViewModel

class MaterialCreate : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_create)

        val etDescripcio = findViewById<EditText>(R.id.etDescripcio)
        val etImatge = findViewById<EditText>(R.id.etImatge)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val desc = etDescripcio.text.toString()
            val img = etImatge.text.toString()

            if (desc.isNotEmpty() && img.isNotEmpty()) {
                // Creamos el objeto Material (id 0 para POST)
                val nuevoMaterial = Material(id = 0, descripcio = desc, imatge = img)

                // Guardamos el material
                viewModel.guardarMaterial(nuevoMaterial)

                Toast.makeText(this, "Guardando material...", Toast.LENGTH_SHORT).show()

                // Volver a la lista de materiales
                finish()
            } else {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}