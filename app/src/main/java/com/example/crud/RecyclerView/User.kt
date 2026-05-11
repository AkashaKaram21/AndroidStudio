package com.example.crud.RecyclerView

data class User(
    val id: Int?,
    val nom: String,
    val rol: String?,
    val password: String
)

data class Material(
    val id: Int = 0,
    val descripcio: String,
    val imatge: String
)