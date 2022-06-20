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
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.creativegrpcx.perawatcher.*
import com.creativegrpcx.perawatcher.data.Date
import com.creativegrpcx.perawatcher.data.Time
import com.creativegrpcx.perawatcher.databinding.ActivityTransactionBinding
import com.creativegrpcx.perawatcher.databinding.TransactionInputLayoutBinding
import com.creativegrpcx.perawatcher.repository.entities.Transaction
import com.creativegrpcx.perawatcher.types.CategoryType
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModel
import org.w3c.dom.Text
import java.lang.Exception
import java.time.LocalDate

private lateinit var binding : ActivityTransactionBinding
private lateinit var includeBinding : TransactionInputLayoutBinding


class TransactionActivity : BaseActivity() , DateTimeInterface{

    private val globalViewModel: GlobalViewModel by viewModels {
         globalViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        includeBinding = binding.transactionLayout

        includeBinding.transactionAddButton.setOnClickListener {
            globalViewModel.insertData(
                Transaction(
                    includeBinding.transactionTitle.editText?.text.toString(),
                    translateCategories(),
                    includeBinding.transactionAmount.editText?.text.toString().toFloat(),
                    includeBinding.transactionDate.editText?.text.toString(),
                    includeBinding.transactionTime.editText?.text.toString()
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
