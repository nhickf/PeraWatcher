package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.data.repository.entities.WalletTransaction
import com.creativegrpcx.perawatcher.domain.model.*
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.utils.AddTransactionEvent
import com.creativegrpcx.perawatcher.domain.utils.AddWalletEvent
import com.creativegrpcx.perawatcher.domain.utils.Response
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute
import com.creativegrpcx.perawatcher.ui.utils.Constants
import com.creativegrpcx.perawatcher.ui.utils.formatDecimalSeparator
import com.creativegrpcx.perawatcher.ui.utils.removeComma
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class GlobalViewModel @Inject constructor(
    application: Application,
    private val repository: DataRepository
) : AndroidViewModel(application) {

    private val _routeState = MutableStateFlow(RouteState())
    private val _dashBoardState = MutableStateFlow(DashboardState())
    private val _walletState = MutableStateFlow(WalletState())
    private val _historyState = MutableStateFlow(HistoryState())
    private val _addTransactionState = MutableStateFlow(AddTransactionState())
    private val _addWalletState = MutableStateFlow(AddWalletState())
    private val _errorState = MutableSharedFlow<ErrorState>()

    val addTransactionState = _addTransactionState.asStateFlow()
    val routeState = _routeState.asStateFlow()
    val dashBoardState = _dashBoardState.asStateFlow()
    val historyState = _historyState.asStateFlow()
    val walletState = _walletState.asStateFlow()
    val addWalletState = _addWalletState.asStateFlow()
    val errorState = _errorState.asSharedFlow()

    init {
        loadDashboardState()
        loadHistoryScreen()
        loadWallet()
    }

    fun updateCurrentRoute(newRoute: ScreenRoute?) {
        _routeState.update { screen ->
            screen.copy(
                oldRoute = if (newRoute == null) {
                    null
                } else {
                    if(screen.currentRoute.isPopUp) {
                        screen.oldRoute
                    } else {
                        screen.currentRoute
                    }
                } ,
                currentRoute = newRoute ?: screen.oldRoute ?: NavigationRoute.Dashboard.withoutArgs,
                isRouteChange = newRoute != null && newRoute != screen.currentRoute,
                isNavigateUp = newRoute == null
            )
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
                            val data = response.data!!.filter { transaction ->
                                transaction.date == Constants.currentDate.formattedDate
                            }
                            it.copy(
                                isLoading = false,
                                transactions = data,
                                todayExpenses = calculateExpenses(data)
                            )
                        }
                    }
                }.collect()
        }
    }

    private fun loadHistoryScreen() {
        viewModelScope.launch {
            repository.getTransactions()
                .onEach { response ->
                    val groupedTransaction =
                        response.data?.groupBy { it.category.name } ?: emptyMap()
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


    fun onAddTransactionEventHandler(currentEvent: AddTransactionEvent) {
        if (currentEvent is AddTransactionEvent.SaveTransaction) {
            val state = _addTransactionState.value
            insertData(
                Transaction(
                    title = state.titleValue,
                    category = state.selectedCategory ?: CategoryType.Others,
                    amount = state.expensesAmount,
                    date = state.date.formattedDate,
                    time = state.time.formattedTime,
                    notes = state.extraNotes,
                    walletId = state.walletId,
                ),
                onComplete = {
                    _addTransactionState.value = AddTransactionState()
                    currentEvent.onComplete()
                },
                onError = { error, scope ->
                    scope.launch {
                        _errorState.emit(
                            ErrorState(
                                isShowed = true,
                                message = "${error.message}"
                            )
                        )
                    }
                }
            )
        }

        _addTransactionState.update { state ->
            when (currentEvent) {
                is AddTransactionEvent.AmountChange -> state.copy(
                    expensesAmount = currentEvent.amount
                )
                is AddTransactionEvent.CategoryChange -> state.copy(
                    selectedCategory = currentEvent.categoryType
                )
                is AddTransactionEvent.DateChange -> state.copy(
                    date = currentEvent.date
                )
                is AddTransactionEvent.NoteChange -> state.copy(
                    extraNotes = currentEvent.note
                )
                is AddTransactionEvent.TimeChange -> state.copy(
                    time = currentEvent.time
                )
                is AddTransactionEvent.TitleChange -> state.copy(
                    titleValue = currentEvent.title
                )
                is AddTransactionEvent.WalletChange -> state.copy(
                    walletId = currentEvent.walletId
                )
                is AddTransactionEvent.SaveTransaction -> {
                    state
                }

            }
        }
    }

    fun onAddWalletEventHandler(event: AddWalletEvent) {
        if (event is AddWalletEvent.SaveWallet) {
            viewModelScope.launch {
                val state = _addWalletState.value
                insertWallet(
                    Wallet(
                        walletName = state.walletName,
                        walletAmount = state.walletAmount,
                        walletType = state.walletType,
                        isEnabled = true,
                        isPrimary = false,
                        walletId =  UUID.randomUUID().toString()
                    ),
                    onComplete = {
                        _addWalletState.value = AddWalletState()
                        event.onComplete()
                    },
                    onError = { error, scope ->
                        scope.launch {
                            _errorState.emit(
                                ErrorState(
                                    isShowed = true,
                                    message = "${error.message}"
                                )
                            )
                        }
                    }
                )
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

    private fun calculateExpenses(transactions: List<Transaction>?): String {
        return String.format(
            "%.2f",
            transactions?.sumOf { transaction ->
                transaction.amount.removeComma()
            }
        )
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

    private fun insertData(
        vararg transaction: Transaction,
        onComplete: () -> Unit,
        onError: (e: Error, scope: CoroutineScope) -> Unit
    ) {
        viewModelScope.launch {
            try {
                repository.insertTransaction(
                    transaction = transaction
                ) {
                    onComplete()
                }
            } catch (e: Exception) {
                onError(Error(e.localizedMessage), this)
            }
        }
    }

    private fun insertWallet(
        vararg wallet: Wallet,
        onComplete: () -> Unit,
        onError: (e: Error, scope: CoroutineScope) -> Unit
    ) {
        viewModelScope.launch {
            try {
                repository.insertWallet(
                    wallet = wallet
                ) {
                    onComplete()
                }
            } catch (e: Exception) {
                onError(Error(e.localizedMessage), this)
            }
        }
    }
}
