package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import javax.inject.Inject

class GlobalViewModelFactory @Inject constructor(
    private val application: Application,
    private val repository: DataRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when (modelClass) {
            DashboardViewModel::class.java -> DashboardViewModel(application,repository) as T
            HistoryViewModel::class.java -> HistoryViewModel(application,repository) as T
            WalletViewModel::class.java -> WalletViewModel(application,repository) as T
            AddTransactionViewModel::class.java -> AddTransactionViewModel(application, repository) as T
            AddWalletViewModel::class.java -> AddWalletViewModel(application, repository) as T
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
