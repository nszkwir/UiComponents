package com.spitzer.uicomponents.searchCard.data

class SearchDataModel(
    val id: Int,
    val apellido: String,
    val nombre: String,
    val dni: String,
    val email: String? = "undefined",
    val telefono: String? = "undefined"
)