package com.example.crud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.crud.ViewModel.ViewModel

class LogInFragment : Fragment() {

    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val etUser = view.findViewById<EditText>(R.id.etUsuari)
        val etPass = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        viewModel.loginResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(requireContext(), "Bienvenido", Toast.LENGTH_SHORT).show()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MaterialFragment())
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Error: Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogin.setOnClickListener {
            val user = etUser.text.toString()
            val pass = etPass.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                viewModel.login(user, pass)
            } else {
                Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}