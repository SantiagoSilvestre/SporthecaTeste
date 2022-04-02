package br.com.me.san.sporthecamatch.data.services

import br.com.me.san.sporthecamatch.data.model.Retorno
import retrofit2.http.GET
import retrofit2.http.Path

interface RetornoService {
    @GET("/teste/teste.json")
    suspend fun getData(): Retorno
}