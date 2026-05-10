package com.example.proba.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.RecyclerView.Reserva
import com.example.proba.Retrofit.ItemAPI
import com.example.proba.Retrofit.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _reserva = MutableLiveData<List<Reserva>>(emptyList())
    val reserva: LiveData<List<Reserva>> = _reserva

    fun registraUser(email : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = User(
                    id = 0,
                    nom = email.trim(),
                    rol = null,
                    password = password.trim()
                )

                val response = ItemAPI.API().registrar(request)

                if (response.isSuccessful) {
                    val userResponse = response.body()

                    withContext(Dispatchers.Main) {
                        _user.value = userResponse

                        if (userResponse != null) {
                            cargarReserva(userResponse.id!!)
                        }
                    }
                } else {
                    Log.e("API", "Error Login: ${response.code()}")
                }
            } catch (e : Exception){
                Log.e("API", "Error de connexió Login", e)
            }
        }
    }

    fun cargarReserva(idUsuario: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ItemAPI.API().obtenerReserves(idUsuario)

                if (response.isSuccessful) {
                    val lista = response.body() ?: emptyList()

                    withContext(Dispatchers.Main) {
                        _reserva.value = lista
                    }
                } else {
                    Log.e("API", "Error Reserves: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error de connexió Reserves", e)
            }
        }
    }
}