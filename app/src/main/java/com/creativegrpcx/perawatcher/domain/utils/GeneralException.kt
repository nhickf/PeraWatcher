package com.creativegrpcx.perawatcher.domain.utils

sealed class GeneralException(errorMessage : String) : Exception(errorMessage) {
    data class EmptyTransaction(private val errorMessage : String) : GeneralException(errorMessage)
    data class IncompleteTransaction(private val errorMessage : String) : GeneralException(errorMessage)
    data class FailedExecute(private val errorMessage : String) : GeneralException(errorMessage)
}
