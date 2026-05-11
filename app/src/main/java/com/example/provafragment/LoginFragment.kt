package com.example.provafragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.provafragment.R
import com.example.provafragment.ViewModel.ViewModel


class LoginFragment : Fragment() {

    // Compartimos el ViewModel con toda la Activity
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflamos el layout igual que hacíamos con setContentView
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // 2. Buscamos las vistas por su ID
        val etEmail = view.findViewById<EditText>(R.id.etUsuari)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        // 3. Configurar el botón de login
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                viewModel.registraUser(email, pass)
            } else {
                Toast.makeText(requireContext(), "Falten dades", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Observar la respuesta del servidor
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                // Si el login es correcto, pasamos al Fragment de Reservas
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ReservesFragment())
                    .commit()
            }
        }

        return view
    }
}