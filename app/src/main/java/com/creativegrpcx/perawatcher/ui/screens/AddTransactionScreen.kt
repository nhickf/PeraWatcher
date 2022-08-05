package com.creativegrpcx.perawatcher.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.text.format.DateFormat.is24HourFormat
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
import com.creativegrpcx.perawatcher.domain.model.Date
import com.creativegrpcx.perawatcher.domain.model.Time
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.utils.AddTransactionEvent
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.ui.components.ChipVerticalGrid
import com.creativegrpcx.perawatcher.ui.utils.Constants
import java.text.DateFormat

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

    val state = viewModel.addTransactionState.collectAsState().value
    val currentDate = Constants.currentDate
    val currentTime = Constants.currentTime

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
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
        { _: TimePicker, hour: Int, minute: Int ->
            viewModel.onAddTransactionEventHandler(AddTransactionEvent.TimeChange(
                Time(
                    hourOfDay = hour,
                    minute = minute
                )
            ))
        }, currentTime.hourOfDay, currentTime.minute, true
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

        Text(text = "Title")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.titleValue,
            label = {
                TextFieldTextPlaceHolder(text = "Put the title here")
            },
            onValueChange = { value ->
                viewModel.onAddTransactionEventHandler(AddTransactionEvent.TitleChange(value))
            },

            colors = TextFieldDefaults.outlinedTextFieldColors(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {

                }
            )
        )

        Text(text = "Categories")

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

        Text(text = "Expenses")

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.expensesAmount,
            onValueChange = { value ->
                viewModel.onAddTransactionEventHandler(AddTransactionEvent.AmountChange(value))
            },
            label = {
                TextFieldTextPlaceHolder(text = "Put the amount here")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Decimal
            ),
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                text = "Date"
            )

            Text(
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

        Text(
            text = "Optional"
        )

        Text(
            text = "Notes"
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
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
                    viewModel.onAddTransactionEventHandler(AddTransactionEvent.SaveTransaction)
                }
            )
        )

        FilledIconButton(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onClick = {  
                viewModel.onAddTransactionEventHandler(AddTransactionEvent.SaveTransaction)
            }) {
            Text(text = "Press to save your transaction")
        }

    }
}
@Composable
fun TextFieldTextPlaceHolder(
    text: String,
    fontSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Normal,

    ){
    Text(
        text = text,
        fontWeight = fontWeight,
    )
}