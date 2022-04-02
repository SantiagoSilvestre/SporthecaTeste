package br.com.me.san.sporthecamatch.data.repositories

import android.os.RemoteException
import br.com.me.san.sporthecamatch.data.model.Retorno
import br.com.me.san.sporthecamatch.data.services.RetornoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RetornoRepositoryImpl(private val service:RetornoService): RetornoRepository {
    override suspend fun getData() = flow {
        try {
            val retorno = service.getData()
            emit(retorno)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message?: "Não foi possível fazer a busca no momento")
        }

    }
}