package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.domain.model.state.AddWalletState
import com.creativegrpcx.perawatcher.domain.model.state.ErrorState
import com.creativegrpcx.perawatcher.domain.utils.AddWalletEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddWalletViewModel @Inject constructor(
    application: Application,
    private val repository: DataRepository
) : AndroidViewModel(application) {

    private val _addWalletState = MutableStateFlow(AddWalletState())
    val addWalletState = _addWalletState.asStateFlow()

    fun onAddWalletEventHandler(event: AddWalletEvent) {
        if (event is AddWalletEvent.SaveWallet) {
            viewModelScope.launch {
                val state = _addWalletState.value

                viewModelScope.launch {
                    try {
                        repository.insertWallet(
                            wallet = arrayOf(
                                Wallet(
                                    walletName = state.walletName,
                                    walletAmount = state.walletAmount,
                                    walletType = state.walletType,
                                    isEnabled = true,
                                    isPrimary = false,
                                    walletId = UUID.randomUUID().toString()
                                )
                            )
                        ) {
                            _addWalletState.value = AddWalletState()
                            event.onComplete()
                        }
                    } catch (e: Exception) {
//                        onError(Error(e.localizedMessage), this)
//                        scope.launch {
//                            _errorState.emit(
//                                ErrorState(
//                                    isShowed = true,
//                                    message = "${error.message}"
//                                )
//                            )
//                        }
                    }
                }
            }
        } else {
            _addWalletState.update { state ->
                when (event) {
                    is AddWalletEvent.AmountChange -> state.copy(walletAmount = event.amount)
                    is AddWalletEvent.TypeChange -> state.copy(walletType = event.type)
                    is AddWalletEvent.TitleChange -> state.copy(walletName = event.title)
                    else -> state
                }
            }
        }
    }
}