package br.com.me.san.sporthecamatch.data.model

import com.google.gson.annotations.SerializedName

data class Retorno (
        @SerializedName("Status")
        val status: Long,
        @SerializedName("Object")
        val dados: List<Dados>
        )