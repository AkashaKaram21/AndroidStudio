package com.example.provafragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.provafragment.R
import com.example.provafragment.RecyclerView.MyAdapter
import com.example.provafragment.ViewModel.ViewModel


class ReservesFragment : Fragment() {

    // Recuperamos el mismo ViewModel que usamos en el Login
    private val viewModel: ViewModel by activityViewModels()
    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflamos el layout
        val view = inflater.inflate(R.layout.fragment_reserves, container, false)

        // 2. Buscamos el RecyclerView por ID
        val rvReserves = view.findViewById<RecyclerView>(R.id.rvReserves)

        // 3. Configuramos el Adapter
        adapter = MyAdapter(emptyList()) { reserva ->
            // Acción opcional al clicar la reserva
        }

        // 4. Configuramos el RecyclerView
        rvReserves.layoutManager = LinearLayoutManager(requireContext())
        rvReserves.adapter = adapter

        // 5. Observar la lista de reservas
        // Como el ViewModel ya las cargó en el Login, aparecerán automáticamente
        viewModel.reserva.observe(viewLifecycleOwner) { lista ->
            adapter.updateList(lista)
        }

        return view
    }
}