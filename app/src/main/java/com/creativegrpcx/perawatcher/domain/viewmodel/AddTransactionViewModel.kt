package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.domain.model.state.AddTransactionState
import com.creativegrpcx.perawatcher.domain.model.state.transformToTransaction
import com.creativegrpcx.perawatcher.domain.utils.AddTransactionEvent
import com.creativegrpcx.perawatcher.domain.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    application: Application,
    private val repository: DataRepository
) : AndroidViewModel(application) {

    private val _addTransactionState = MutableStateFlow(AddTransactionState())
    val addTransactionState = _addTransactionState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllWallet()
                .onEach { response ->
                    when (response) {
                        is Response.Loading -> {}
                        is Response.Error -> {}
                        else -> _addTransactionState.update {
                            it.copy(
                                wallet = response.data?: emptyList(),
                            )
                        }
                    }
                }.collect()
        }
    }

    fun onAddTransactionEventHandler(currentEvent: AddTransactionEvent) {
        _addTransactionState.let {
            if (currentEvent is AddTransactionEvent.SaveTransaction) {
                val state = it.value

                viewModelScope.launch {
                    try {
                        repository.insertTransaction(
                            transaction = arrayOf(state.transformToTransaction()),
                            onComplete = {
                                _addTransactionState.value = AddTransactionState()
                                currentEvent.onComplete()
                            }
                        )
                    } catch (e: Exception) {
//                    _errorState.emit(
//                        ErrorState(
//                            isShowed = true,
//                            message = "${error.message}"
//                        )
//                    )
                    }
                }
            }

            it.update { state ->
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
    }
}