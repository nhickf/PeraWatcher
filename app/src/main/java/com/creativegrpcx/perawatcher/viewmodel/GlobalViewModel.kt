package com.creativegrpcx.perawatcher.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.creativegrpcx.perawatcher.data.DateTime
import com.creativegrpcx.perawatcher.repository.entities.Transaction
import com.creativegrpcx.perawatcher.repository.UserRepository
import com.creativegrpcx.perawatcher.repository.entities.SectionedTransaction
import com.creativegrpcx.perawatcher.repository.entities.Wallet
import com.creativegrpcx.perawatcher.types.CategoryType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class GlobalViewModel @Inject constructor(
    application: Application,
    private val repository: UserRepository
) : AndroidViewModel(application) {

    private val _uiStateTransaction = MutableStateFlow<List<Transaction>>(arrayListOf())
    private val _uiStateSectionTransaction =
        MutableStateFlow<List<SectionedTransaction>>(arrayListOf())
    val uiStateTransaction: StateFlow<List<Transaction>> = _uiStateTransaction
    val uiStateSectionedTransaction: StateFlow<List<SectionedTransaction?>> = _uiStateSectionTransaction

//    private val _uiStateSpecificTransaction = MutableStateFlow<List<Transaction>>(arrayListOf())
//    val uiStateSpecificTransaction: StateFlow<List<Transaction>> = _uiStateSpecificTransaction

    private val _uiStateWallet = MutableStateFlow<List<Wallet>>(arrayListOf())
    val uiStateWallet: StateFlow<List<Wallet>> = _uiStateWallet

    fun loadTransactions(vararg categories: CategoryType) {
        viewModelScope.launch {
            repository.getTransactions(*categories).collect {
                _uiStateTransaction.value = it
            }
        }
    }

    fun loadSectionTransactions() {
        viewModelScope.launch {
            repository.getTransactions().collect {
                _uiStateSectionTransaction.value = CategoryType.values().map {  type ->
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

    fun insertData(transaction: Transaction) {
        viewModelScope.launch {
            repository.insertTransaction(
                transaction
            )
        }
    }

    fun insertWallet(wallet: Wallet) {
        viewModelScope.launch {
            repository.insertWallet(wallet)
        }
    }
}
