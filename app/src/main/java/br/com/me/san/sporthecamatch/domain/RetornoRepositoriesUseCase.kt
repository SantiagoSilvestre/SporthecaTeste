package br.com.me.san.sporthecamatch.domain

import br.com.me.san.sporthecamatch.core.UseCase
import br.com.me.san.sporthecamatch.data.model.Retorno
import br.com.me.san.sporthecamatch.data.repositories.RetornoRepository
import kotlinx.coroutines.flow.Flow


class RetornoRepositoriesUseCase(private val repository: RetornoRepository) :
UseCase<String, Retorno>()
{

    override suspend fun execute(param: String): Flow<Retorno> {
        return repository.getData()
    }
}