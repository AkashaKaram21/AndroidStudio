package com.example.crud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.RecyclerView.MyAdapter
import com.example.crud.ViewModel.ViewModel
import kotlin.getValue

class MaterialFragment : Fragment() {

    private val viewModel: ViewModel by activityViewModels()
    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_material, container, false)

        // 1. Buscamos el RecyclerView
        val rvMaterial = view.findViewById<RecyclerView>(R.id.rvMaterials)

        // 2. Configuramos el Adapter
        adapter = MyAdapter(
            emptyList(),
            onItemClick = { material ->
                // Acción al pulsar un elemento (ej: ir a detalles)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MaterialCreate())
                    .addToBackStack(null)
                    .commit()
            },
            onDeleteClick = { id ->
                // CORRECCIÓN: Llamamos al ViewModel para borrar, no a la vista
                viewModel.deleteMaterial(id)
                Toast.makeText(requireContext(), "Eliminando...", Toast.LENGTH_SHORT).show()
            }
        )

        // 3. Configuramos el RecyclerView
        rvMaterial.layoutManager = LinearLayoutManager(requireContext())
        rvMaterial.adapter = adapter

        // 4. Observar la lista de materiales
        // Usamos "materials" que es el LiveData que definimos en el ViewModel
        viewModel.materials.observe(viewLifecycleOwner) { lista ->
            adapter.updateList(lista) // Asegúrate de que tu adapter tenga esta función
        }

        // 5. Cargar los datos por primera vez
        viewModel.fetchMaterials()

        return view
    }
}