package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.domain.model.state.DashboardState
import com.creativegrpcx.perawatcher.domain.utils.Response
import com.creativegrpcx.perawatcher.ui.utils.Constants
import com.creativegrpcx.perawatcher.ui.utils.removeComma
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val repository: DataRepository
) : AndroidViewModel(application)  {

    private val _dashBoardState = MutableStateFlow(DashboardState())
    val dashBoardState = _dashBoardState.asStateFlow()


    init {
        loadDashboardState()
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

    private fun calculateExpenses(transactions: List<Transaction>?): String {
        return String.format(
            "%.2f",
            transactions?.sumOf { transaction ->
                transaction.amount.removeComma()
            }
        )
    }


}