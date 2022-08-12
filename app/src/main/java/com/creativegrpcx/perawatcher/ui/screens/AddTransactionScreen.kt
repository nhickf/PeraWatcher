package com.creativegrpcx.perawatcher.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.domain.model.Date
import com.creativegrpcx.perawatcher.domain.model.Time
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.utils.AddTransactionEvent
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.ui.components.ChipVerticalGrid
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.utils.Constants
import com.creativegrpcx.perawatcher.ui.utils.Constants.TextFieldTitle
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddTransactionScreen(
    viewModel: GlobalViewModel = viewModel()
) {
    val scrollState = rememberScrollState()

    val dateSource = remember {
        MutableInteractionSource()
    }
    val timeSource = remember {
        MutableInteractionSource()
    }

    val focusManager = LocalFocusManager.current
    val state = viewModel.addTransactionState.collectAsState().value
    val wallets = viewModel.walletState.collectAsState().value.wallets
    val currentDate = Constants.currentDate
    val currentTime = Constants.currentTime

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        R.style.CalenderViewCustom,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            focusManager.clearFocus()
            viewModel.onAddTransactionEventHandler(AddTransactionEvent.DateChange(
                Date(
                    year = mYear,
                    month = mMonth,
                    dayOfMonth = mDayOfMonth,
                )
            ))
        }, currentDate.year, currentDate.month, currentDate.dayOfMonth
    )

    if (dateSource.collectIsPressedAsState().value){
        mDatePickerDialog.show()
    }

    val mTimePickerDialog = TimePickerDialog(
        LocalContext.current,
        R.style.CalenderViewCustom,
        { _: TimePicker, hour: Int, minute: Int ->
            focusManager.clearFocus()
            viewModel.onAddTransactionEventHandler(AddTransactionEvent.TimeChange(
                Time(
                    hourOfDay = hour,
                    minute = minute
                )
            ))
        }, currentTime.hourOfDay, currentTime.minute, false
    )
    if (timeSource.collectIsPressedAsState().value){
        mTimePickerDialog.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
            .verticalScroll(
                state = scrollState,
                enabled = true
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        TextFieldTitle(text = "Title")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            value = state.titleValue,
            maxLines = 2,
            label = {
                TextFieldTextPlaceHolder(text = "Put the title here")
            },
            onValueChange = { value ->
                viewModel.onAddTransactionEventHandler(AddTransactionEvent.TitleChange(value))
            },

            colors = TextFieldDefaults.outlinedTextFieldColors(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone  = {
                    focusManager.clearFocus()
                }
            )
        )

        TextFieldTitle(text = "Categories")

        ChipVerticalGrid(
            horizontalSpacing = 8.dp,
            verticalSpacing = 0.dp
        ) {
            CategoryType.values().forEach { category ->
                FilterChip(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.wrapContentSize(),
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector
                                .vectorResource(id = Constants.categoryIcon(category)),
                            contentDescription = "",
                        )
                    },
                    onClick = {
                        viewModel.onAddTransactionEventHandler(AddTransactionEvent.CategoryChange(category))
                    },
                    label = {
                        Text(
                            text = category.name,
                            maxLines = 1,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    },
                    selected = state.selectedCategory == category
                )
            }
        }

        TextFieldTitle(text = "Expenses")

        if(wallets.isNotEmpty()){
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(wallets){ walletTransaction ->
                    val wallet = walletTransaction.wallet
                    FilterChip(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.wrapContentSize(),
                        leadingIcon = {
                            Icon(
                                imageVector = ImageVector
                                    .vectorResource(id = Constants.walletIcon(wallet.walletType)),
                                contentDescription = "",
                            )
                        },
                        onClick = {
                            viewModel.onAddTransactionEventHandler(AddTransactionEvent.WalletChange(wallet.walletId))
                        },
                        label = {
                            Text(
                                text = wallet.walletName,
                                maxLines = 1,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                        },
                        selected = state.walletId == wallet.walletId
                    )
                }
            }
        }else{
            LaunchedEffect(true) {
                viewModel.updateCurrentRoute(NavigationRoute.PopUpAddWallet.withoutArgs)
            }
        }


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            value = state.expensesAmount,
            onValueChange = { value ->
                viewModel.onAddTransactionEventHandler(AddTransactionEvent.AmountChange(value.replace(",","").trim()))
            },
            label = {
                TextFieldTextPlaceHolder(text = "Put the amount here")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Decimal
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextFieldTitle(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                text = "Date"
            )

            TextFieldTitle(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                text = "Time"
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                value = state.date.formattedDate,
                onValueChange = {
                },
                readOnly = true,
                label = {
                    TextFieldTextPlaceHolder(text = "Pick date")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(),
                interactionSource = dateSource
            )

            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                value = state.time.formattedTime,
                onValueChange = { },
                readOnly = true,
                label = {
                    TextFieldTextPlaceHolder(text = "Pick time")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(),
                interactionSource = timeSource
            )
        }

        TextFieldTitle(
            text = "Notes"
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            value = state.extraNotes,
            onValueChange = { value ->
                viewModel.onAddTransactionEventHandler(
                    AddTransactionEvent.NoteChange(value)
                )
            },
            label = {
                TextFieldTextPlaceHolder(text = "Put the your extra notes here")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    viewModel.onAddTransactionEventHandler(AddTransactionEvent.SaveTransaction{
                        viewModel.updateCurrentRoute(null)
                    })
                }
            )
        )

        FilledIconButton(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onClick = {  
                viewModel.onAddTransactionEventHandler(AddTransactionEvent.SaveTransaction{
                    viewModel.updateCurrentRoute(null)
                })
            }) {
            Text(text = "Press to save your transaction")
        }

    }

    BackHandler {
        viewModel.updateCurrentRoute(null)
    }

}
@Composable
fun TextFieldTextPlaceHolder(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,

    ){
    Text(
        text = text,
        fontWeight = fontWeight,
    )
}
