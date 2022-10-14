package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.creativegrpcx.perawatcher.data.repository.entities.WalletTransaction
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.domain.model.state.WalletState
import com.creativegrpcx.perawatcher.domain.utils.Response
import com.creativegrpcx.perawatcher.ui.utils.removeComma
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WalletViewModel (
    application: Application,
    private val repository: DataRepository
) : AndroidViewModel(application)  {

    private val _walletState = MutableStateFlow(WalletState())
    val walletState = _walletState.asStateFlow()

    init {
        loadWallet()
    }

    private fun loadWallet() {
        viewModelScope.launch {
            repository.getAllWallet()
                .onEach { response ->
                    when (response) {
                        is Response.Loading -> {
                            _walletState.update {
                                it.copy(
                                    isLoading = response.isLoading,
                                    wallets = response.data!!,
                                    totalNetWorth = calculateNetWorth(response.data)
                                )
                            }
                        }
                        is Response.Error -> {
                            _walletState.update {
                                it.copy(
                                    isLoading = false,
                                    wallets = response.data!!,
                                    error = Error(response.exception),
                                )
                            }
                        }
                        else -> _walletState.update {
                            it.copy(
                                isLoading = false,
                                wallets = response.data!!,
                                totalNetWorth = calculateNetWorth(response.data)
                            )
                        }
                    }
                }.collect()
        }
    }

    private fun calculateNetWorth(wallets: List<WalletTransaction>?): String {
        return String.format(
            "%.2f",
            wallets?.sumOf { walletTransaction ->
                walletTransaction
                    .wallet.walletAmount
                    .removeComma()
            }
        )
    }
}