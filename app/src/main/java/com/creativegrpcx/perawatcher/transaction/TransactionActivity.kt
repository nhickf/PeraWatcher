package com.creativegrpcx.perawatcher.transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.asLiveData
import com.creativegrpcx.perawatcher.*
import com.creativegrpcx.perawatcher.data.Date
import com.creativegrpcx.perawatcher.data.Time
import com.creativegrpcx.perawatcher.databinding.ActivityAddWalletBinding.inflate
import com.creativegrpcx.perawatcher.databinding.ActivityTransactionBinding
import com.creativegrpcx.perawatcher.databinding.TransactionInputLayoutBinding
import com.creativegrpcx.perawatcher.repository.entities.Transaction
import com.creativegrpcx.perawatcher.repository.entities.Wallet
import com.creativegrpcx.perawatcher.types.CategoryType
import com.creativegrpcx.perawatcher.types.WalletType
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModel
import com.google.android.material.chip.Chip
import org.w3c.dom.Text
import java.lang.Exception
import java.time.LocalDate
import java.util.zip.Inflater

private lateinit var binding : ActivityTransactionBinding
private lateinit var includeBinding : TransactionInputLayoutBinding


class TransactionActivity : BaseActivity() , DateTimeInterface{

    private data class WalletChip(
        val wallet: Wallet,
        val chip: Chip
    )

    private lateinit var wallet : Wallet
    private val chipsList : ArrayList<WalletChip> = ArrayList()

    private val globalViewModel: GlobalViewModel by viewModels {
         globalViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        globalViewModel.loadWallet()

        includeBinding = binding.transactionLayout

        includeBinding.transactionAddButton.setOnClickListener {
            globalViewModel.insertData(
                Transaction(
                    includeBinding.transactionTitle.editText?.text.toString(),
                    translateCategories(),
                    includeBinding.transactionAmount.editText?.text.toString().toFloat(),
                    includeBinding.transactionDate.editText?.text.toString(),
                    includeBinding.transactionTime.editText?.text.toString(),
                    wallet.walletId
                )
            )
            closeActivity()
        }

        includeBinding.transactionDate.editText?.setOnClickListener {
            DatePickerFragment(this).show(supportFragmentManager, "DatePicker")
        }
        includeBinding.transactionTime.editText?.setOnClickListener {
            TimePickerFragment(this).show(supportFragmentManager, "TimePicker")
        }
        binding.topAppBar.setNavigationOnClickListener {
            closeActivity()
        }

        binding.transactionLayout.transactionExpensesChipGroup.let {
            globalViewModel.uiStateWallet.asLiveData().observe(this){ wallets ->
                wallets?.forEachIndexed { index, wallet ->
                    val chip = layoutInflater.inflate(R.layout.layout_chip_transaction, it, false) as Chip
                    it.addView(
                        chip.apply {
                            this.id = wallet.walletType.ordinal + index
                            this.text = wallet.walletName
                            this.chipIcon = ResourcesCompat.getDrawable(resources, generateWalletIcon(wallet.walletType),application.theme)
                        }
                    )
                    chipsList.add(WalletChip(wallet, chip))
                }
            }

            it.setOnCheckedStateChangeListener { group, _ ->
                chipsList.find { walletChip -> walletChip.chip.id == group.checkedChipId }?.let { wc ->
                    wallet = wc.wallet
                }
            }
        }
    }

    private fun generateWalletIcon(type : WalletType) : Int {
        return when (type) {
            WalletType.CASH -> R.drawable.cash_100
            WalletType.CREDIT_CARD -> R.drawable.credit_card
            WalletType.SAVINGS -> R.drawable.bank
            else -> R.drawable.baseline_question_mark_24
        }
    }

    private fun translateCategories () : CategoryType {
       return when(binding.transactionLayout.transactionCategory.checkedChipId){
           includeBinding.categoryAppliances.id -> CategoryType.Appliance
           includeBinding.categoryBeauty.id -> CategoryType.BeautyAndPersonalCare
           includeBinding.categoryBills.id -> CategoryType.Bills
           includeBinding.categoryComputer.id -> CategoryType.ComputerAndITAccessories
           includeBinding.categoryElectronics.id -> CategoryType.Electronics
           includeBinding.categoryFood.id -> CategoryType.FoodAndGroceries
           includeBinding.categoryFurnitures.id -> CategoryType.Furniture
           includeBinding.categoryFashion.id -> CategoryType.Fashion
           includeBinding.categoryHealth.id -> CategoryType.Health
           includeBinding.categoryHob.id -> CategoryType.HobbiesAndLeisure
           includeBinding.categoryPet.id -> CategoryType.PetSupplies
           includeBinding.categorySubscription.id -> CategoryType.Subscription
           else -> CategoryType.Others
        }

    }

    override fun onDateSet(view: DatePicker?, date: Date) {
        includeBinding.transactionDate.editText?.text = editable(date.formattedDate)
    }

    override fun onTimeSet(view: TimePicker, time: Time) {
        includeBinding.transactionTime.editText?.text = editable(time.formattedTime)

    }

    private fun closeActivity(){
        finish()
    }

}
