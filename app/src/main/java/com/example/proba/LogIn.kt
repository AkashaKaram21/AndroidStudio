package com.example.proba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.proba.ViewModel.ViewModel

class LogIn : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val etUsuari = findViewById<EditText>(R.id.etUsuari)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            viewModel.registraUser(
                etUsuari.text.toString(),
                etPassword.text.toString()
            )
        }

        viewModel.user.observe(this) { user ->
            if (user != null) {
                val intent = Intent(this, Reserves::class.java)
                intent.putExtra("USUARI_ID", user.id)
                startActivity(intent)
                finish()
            }
        }
    }
}