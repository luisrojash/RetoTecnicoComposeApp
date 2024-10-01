package com.lerp.infrastructure.repository.response

import com.google.gson.annotations.SerializedName



data class GetProductListResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("urlImage") val urlImage: String,
    @SerializedName("cliente") val cliente: Boolean,
    @SerializedName("empleado") val empleado: Boolean,
)

