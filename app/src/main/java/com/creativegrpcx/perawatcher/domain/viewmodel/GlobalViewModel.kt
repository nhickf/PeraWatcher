package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.*
import androidx.navigation.NavHostController
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.data.repository.entities.SectionedTransaction
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.model.RouteState
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute
import com.creativegrpcx.perawatcher.ui.utils.Constants
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class GlobalViewModel @Inject constructor(
    application: Application,
    private val repository: DataRepository
) : AndroidViewModel(application) {

    private val _uiStateTransaction = MutableStateFlow<List<Transaction>>(arrayListOf())
    private val _uiStateSectionTransaction =
        MutableStateFlow<List<SectionedTransaction>>(arrayListOf())
    private val _uiStateWallet = MutableStateFlow<List<Wallet>>(arrayListOf())
    private val _uiStateRoute = MutableStateFlow(RouteState())


    //public flow
    val uiStateTransaction = _uiStateTransaction.asStateFlow()
    val uiStateSectionedTransaction = _uiStateSectionTransaction.asStateFlow()
    val uiStateWallet = _uiStateWallet.asStateFlow()
    val uiStateRoute = _uiStateRoute.asStateFlow()

    init {
        loadTransactions()
        loadWallet()
    }

    fun updateCurrentRoute(newRoute: ScreenRoute) {
        _uiStateRoute.update { oldRoute->
            oldRoute.copy(
                newRoute = newRoute
            )
        }
    }

    fun loadTransactions(vararg categories: CategoryType) {
        viewModelScope.launch {
            repository.getTransactions(*categories).collect {
                Log.e("Items", "$it")
                _uiStateTransaction.value = it
            }
        }
    }

    fun loadSectionTransactions() {
        viewModelScope.launch {
            repository.getTransactions().collect {
                _uiStateSectionTransaction.value = CategoryType.values().map { type ->
                    return@map SectionedTransaction(
                        type.id.toString(),
                        type,
                        it.filter { tt -> tt.category === type })
                }.filter { sectionedTransaction -> sectionedTransaction.sectionItems.isNotEmpty() }
            }
        }
    }

    fun loadWallet() {
        viewModelScope.launch {
            repository.getAllWallet().collect {
                _uiStateWallet.value = it
            }
        }
    }

    fun insertData(vararg transaction: Transaction) {
        viewModelScope.launch {
            try {
                repository.insertTransaction(
                    transaction = transaction
                ) {
                    Log.e("insertData", "success")
                }
            } catch (e: Exception) {
                Log.e("insertData", "$e")
            }
        }
    }

    fun insertWallet(wallet: Wallet) {
        viewModelScope.launch {
            repository.insertWallet(wallet) {

            }
        }
    }
}
