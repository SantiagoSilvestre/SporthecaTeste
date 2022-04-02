package br.com.me.san.sporthecamatch.data.model

import com.google.gson.annotations.SerializedName

data class Player(
    val img: String,
    val name: String,
    val percentual: Double,
    val pos: String,
    val country: String,
    @SerializedName("Barras")
    val barras: Barras
)
