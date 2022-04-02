package br.com.me.san.sporthecamatch.data.repositories

import br.com.me.san.sporthecamatch.data.model.Retorno
import kotlinx.coroutines.flow.Flow

interface RetornoRepository {
    suspend fun getData(): Flow<Retorno>
}