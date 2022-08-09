package com.creativegrpcx.perawatcher.domain.utils

sealed class Response <out T> (val status: ResponseStatus, val data: T?, val message:String?) {

    data class Success<out R>(private val _data: R?): Response<R>(
        status = ResponseStatus.Success,
        data = _data,
        message = null
    )

    data class Error<out R>(private val _data: R?,val exception: String): Response<R>(
        status = ResponseStatus.Error,
        data = _data,
        message = exception
    )

    data class Loading<out R>(val isLoading: Boolean,private val _data: R? = null): Response<R>(
        status = ResponseStatus.Loading,
        data = _data,
        message = null
    )

    data class Failure<out R>(private val _data: R?,val exception: String): Response<R>(
        status = ResponseStatus.Failed,
        data = _data,
        message = exception
    )

}