package com.creativegrpcx.perawatcher.domain.model.state

data class ErrorState(
    val isShowed : Boolean = false,
    val message : String = ""
)
