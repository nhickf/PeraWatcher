package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.domain.model.state.HistoryState
import com.creativegrpcx.perawatcher.domain.utils.Response
import com.creativegrpcx.perawatcher.ui.utils.removeComma
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    application: Application,
    private val repository: DataRepository
) : AndroidViewModel(application)  {

    private val _historyState = MutableStateFlow(HistoryState())
    val historyState = _historyState.asStateFlow()

    init {
        loadHistoryScreen()
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

    private fun calculateExpenses(transactions: List<Transaction>?): String {
        return String.format(
            "%.2f",
            transactions?.sumOf { transaction ->
                transaction.amount.removeComma()
            }
        )
    }

}