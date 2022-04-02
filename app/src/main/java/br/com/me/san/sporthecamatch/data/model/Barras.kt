package br.com.me.san.sporthecamatch.data.model

import com.google.gson.annotations.SerializedName

data class Barras(
    @SerializedName("Copas_do_Mundo_Vencidas")
    val copasDoMundoVencidas: CopasDoMundo,
    @SerializedName("Copas_do_Mundo_Disputadas")
    val copasDoMundoDisputadas: CopasDoMundo
)
