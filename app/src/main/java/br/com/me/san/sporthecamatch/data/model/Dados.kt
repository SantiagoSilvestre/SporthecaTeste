package br.com.me.san.sporthecamatch.data.model

import com.google.gson.annotations.SerializedName

data class Dados (
        @SerializedName("Player")
            val player: Player
        )