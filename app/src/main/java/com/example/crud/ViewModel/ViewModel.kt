package com.example.crud.ViewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.crud.RecyclerView.Material
import com.example.crud.RecyclerView.User
import com.example.crud.Retrofit.ItemAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel : ViewModel() {

    // Usamos el patrón get() para proteger los datos como comentamos antes [1]
    private val _materials = MutableLiveData<List<Material>>()
    val materials: LiveData<List<Material>> get() = _materials

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun login(nombre: String, pass: String) {
        val userRequest = User(id = null, nom = nombre.trim(), rol = null, password = pass.trim())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Se usa ItemAPI.API() según la estructura de tus fuentes [1]
                val response = ItemAPI.API().registrar(userRequest)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        _loginResult.value = true
                    } else {
                        _loginResult.value = false
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _loginResult.value = false
                    Log.e("LoginError", "Error de red: ${e.message}")
                }
            }
        }
    }

    // Obtener todos los materiales
    fun fetchMaterials() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ItemAPI.API().getMaterials()
                if (response.isSuccessful) {
                    _materials.postValue(response.body())
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Error cargando: ${e.message}")
            }
        }
    }

    // Crear o Editar material
    fun guardarMaterial(material: Material) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = if (material.id == 0) {
                    // Si el ID es 0, es un material nuevo (POST)
                    ItemAPI.API().crearMaterial(material)
                } else {
                    // Si ya tiene ID, editamos el existente (PUT)
                    ItemAPI.API().editarMaterial(material.id, material)
                }

                if (response.isSuccessful) {
                    fetchMaterials()
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Error guardando: ${e.message}")
            }
        }
    }

    // Eliminar material
    fun deleteMaterial(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ItemAPI.API().eliminarMaterial(id)

                if (response.isSuccessful) {
                    fetchMaterials()
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Error eliminando: ${e.message}")
            }
        }
    }
}