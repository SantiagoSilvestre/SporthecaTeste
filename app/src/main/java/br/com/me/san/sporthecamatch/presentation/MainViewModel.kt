package br.com.me.san.sporthecamatch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.me.san.sporthecamatch.data.model.Retorno
import br.com.me.san.sporthecamatch.domain.RetornoRepositoriesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val retornoRepositoriesUseCase: RetornoRepositoriesUseCase
) :ViewModel() {

    private val _retorno = MutableLiveData<State>()
    val retorno: LiveData<State> = _retorno

    fun getData(path: String) {
        viewModelScope.launch {
            retornoRepositoriesUseCase(path)
                .onStart {
                    _retorno.postValue(State.Loading)
                }
                .catch {
                    _retorno.postValue(State.Error(it))
                }
                .collect {
                    _retorno.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading: State()
        data class Success(val retorno: Retorno): State()
        data class Error(val error: Throwable): State()
    }

}