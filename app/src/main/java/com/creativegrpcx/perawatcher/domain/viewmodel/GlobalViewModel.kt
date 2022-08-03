package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.data.repository.entities.SectionedTransaction
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.model.DashboardState
import com.creativegrpcx.perawatcher.domain.model.HistoryState
import com.creativegrpcx.perawatcher.domain.model.RouteState
import com.creativegrpcx.perawatcher.domain.model.WalletState
import com.creativegrpcx.perawatcher.domain.utils.Response
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute
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

    private val _routeState = MutableStateFlow(RouteState())
    private val _dashBoardState = MutableStateFlow(DashboardState())
    private val _walletState = MutableStateFlow(WalletState())
    private val _historyState = MutableStateFlow(HistoryState())

    val routeState = _routeState.asStateFlow()
    val dashBoardState = _dashBoardState.asStateFlow()
    val historyState = _historyState.asStateFlow()

    init {
        loadDashboardState()
        loadHistoryScreen()
        loadWallet()
    }

    fun updateCurrentRoute(newRoute: ScreenRoute?) {
        _routeState.update { screen ->
            screen.copy(
                oldRoute = if (newRoute == null) null else screen.currentRoute,
                currentRoute = newRoute ?: screen.oldRoute ?: NavigationRoute.Dashboard.withoutArgs,
                isRouteChange = newRoute != null && newRoute != screen.currentRoute,
                isNavigateUp = newRoute == null
            )
        }
    }

    fun loadHistoryScreen(){
        viewModelScope.launch {
            repository.getTransactions()
                .onEach { response ->
                    val groupedTransaction = response.data?.groupBy { it.category.name } ?: emptyMap()
                    when (response) {
                        is Response.Loading -> {
                            _historyState.update {
                                it.copy(
                                    isLoading = response.isLoading,
                                    groupTransactions = groupedTransaction,
                                    overAllExpenses = calculateExpenses(response.data)
                                )
                            }
                        }
                        is Response.Error -> {
                            _historyState.update {
                                it.copy(
                                    isLoading = false,
                                    groupTransactions = groupedTransaction,
                                    error = Error(response.exception),
                                )
                            }
                        }
                        else -> _historyState.update {
                            it.copy(
                                isLoading = false,
                                groupTransactions = groupedTransaction,
                                overAllExpenses = calculateExpenses(response.data)
                            )
                        }
                    }
                }.collect()
        }
    }


    private fun loadDashboardState() {
        viewModelScope.launch {
            repository.getTransactions()
                .onEach { response ->
                    when (response) {
                        is Response.Loading -> {
                            _dashBoardState.update {
                                it.copy(
                                    isLoading = response.isLoading,
                                    transactions = response.data!!,
                                    todayExpenses = calculateExpenses(response.data)
                                )
                            }
                        }
                        is Response.Error -> {
                            _dashBoardState.update {
                                it.copy(
                                    isLoading = false,
                                    transactions = response.data!!,
                                    error = Error(response.exception),
                                )
                            }
                        }
                        else -> _dashBoardState.update {
                            it.copy(
                                isLoading = false,
                                transactions = response.data!!,
                                todayExpenses = calculateExpenses(response.data)
                            )
                        }
                    }
                }.collect()
        }
    }

    private fun calculateExpenses(transactions : List<Transaction>?) : String{
       return String.format("%.2f",
           transactions?.map { transaction ->
               transaction.amount
           }?.sum() )
    }

//    fun loadSectionTransactions() {
//        viewModelScope.launch {
//            repository.getTransactions().collect {
//                _uiStateSectionTransaction.value = CategoryType.values().map { type ->
//                    return@map SectionedTransaction(
//                        type.id.toString(),
//                        type,
//                        it.filter { tt -> tt.category === type })
//                }.filter { sectionedTransaction -> sectionedTransaction.sectionItems.isNotEmpty() }
//            }
//        }
//    }

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
