package com.creativegrpcx.perawatcher.domain.model.state

sealed class MainState{
    open val isLoading : Boolean = false
    open val error : Error? = null
    open val isNavigateUp : Boolean = false
}
