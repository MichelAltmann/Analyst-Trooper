package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

object ApiError {
    data class GenericException(override val message: String? = null) : Exception()
}