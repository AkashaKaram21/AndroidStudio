package com.example.provafragment.Retrofit

import com.example.provafragment.RecyclerView.Reserva
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ItemService {

    @POST("/login/")
    suspend fun registrar(@Body userRequestDTO : User) : Response<User>
    @GET("reserves/usuari/{id}")
    suspend fun obtenerReserves(@Path("id") id: Int): Response<List<Reserva>>

}