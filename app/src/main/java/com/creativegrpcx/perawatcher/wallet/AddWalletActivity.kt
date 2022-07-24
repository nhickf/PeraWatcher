package com.creativegrpcx.perawatcher.wallet

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.creativegrpcx.perawatcher.BaseActivity
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.databinding.ActivityAddWalletBinding
import com.creativegrpcx.perawatcher.repository.entities.Wallet
import com.creativegrpcx.perawatcher.types.WalletType
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModel

private lateinit var binding : ActivityAddWalletBinding

class AddWalletActivity : BaseActivity() {

    private val globalViewModel: GlobalViewModel by viewModels {
        globalViewModelFactory
    }

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setNavigationOnClickListener {
            closeActivity()
        }

        binding.transactionAddButton.setOnClickListener {
            globalViewModel.insertWallet(
                Wallet(
                    binding.transactionTitle.editText?.text.toString(),
                    binding.transactionAmount.editText?.text.toString().toFloat(),
                    translateWallet(),
                    isEnabled = false, isPrimary = false
                )
            )
            closeActivity()
        }
    }

    private fun translateWallet () : WalletType {
        return when(binding.transactionCategory.checkedChipId){
            binding.categoryCash.id -> WalletType.CASH
            binding.categoryCreditCard.id -> WalletType.CREDIT_CARD
            binding.categorySavings.id -> WalletType.SAVINGS
            else -> WalletType.OTHERS
        }

    }

    private fun closeActivity(){
        finish()
    }

}
