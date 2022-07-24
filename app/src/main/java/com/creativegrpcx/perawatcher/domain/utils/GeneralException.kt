package com.creativegrpcx.perawatcher.domain.utils

sealed class GeneralException(error : Error) : Exception(error) {
    data class EmptyTransaction(private val error : Error) : GeneralException(error)
    data class IncompleteTransaction(private val error : Error) : GeneralException(error)
    data class FailedExecute(private val error : Error) : GeneralException(error)
}
