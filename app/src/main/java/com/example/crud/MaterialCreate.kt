package com.example.crud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.RecyclerView.Material
import com.example.crud.RecyclerView.MyAdapter
import com.example.crud.ViewModel.ViewModel
import kotlin.getValue

class MaterialCreate : Fragment() {

    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflamos la vista
        val view = inflater.inflate(R.layout.fragment_material_create, container, false)

        // 2. Buscamos los componentes (EditText y el Botón de guardar)
        val etDescripcio = view.findViewById<EditText>(R.id.etDescripcio)
        val etImatge = view.findViewById<EditText>(R.id.etImatge)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar) // Asegúrate de tener este ID en tu XML

        // 3. Programamos el clic del botón
        btnGuardar.setOnClickListener {
            val desc = etDescripcio.text.toString()
            val img = etImatge.text.toString()

            if (desc.isNotEmpty() && img.isNotEmpty()) {
                // Creamos el objeto Material (con id 0 para que sea un POST)
                val nuevoMaterial = Material(id = 0, descripcio = desc, imatge = img)

                // Llamamos a la función del ViewModel que definimos anteriormente
                viewModel.guardarMaterial(nuevoMaterial)

                Toast.makeText(requireContext(), "Guardando material...", Toast.LENGTH_SHORT).show()

                // Opcional: Volver atrás después de guardar
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}