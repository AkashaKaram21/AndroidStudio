package com.example.crud.Retrofit

import com.example.crud.RecyclerView.Material
import com.example.crud.RecyclerView.User
import retrofit2.Response
import retrofit2.http.*

interface ItemService {

    @POST("/login/")
    suspend fun registrar(@Body userRequestDTO : User) : Response<User>

    @GET("materials/")
    suspend fun getMaterials(): Response<List<Material>>

    @POST("materials/")
    suspend fun crearMaterial(@Body material: Material): Response<String>

    @PUT("materials/{id}")
    suspend fun editarMaterial(@Path("id") id: Int, @Body material: Material): Response<String>

    @DELETE("materials/{id}")
    suspend fun eliminarMaterial(@Path("id") id: Int): Response<Unit>
}