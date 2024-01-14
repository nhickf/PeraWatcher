package com.creativegrpcx.perawatcher.ui.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.ui.components.OutlineNumberTextField
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AddTransactionScreen(
    id: Int = 5,
) {
   // val viewModel: AddTransactionViewModel = hiltViewModel()

    val scrollState = rememberScrollState()

    var tfTitle by rememberSaveable { mutableStateOf("") }
    var chipCategory by rememberSaveable { mutableStateOf(CategoryType.Others) }
    var tfAmount by rememberSaveable {
        mutableStateOf("0")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            value = tfTitle,
            label = { Text(text = stringResource(R.string.add_transaction_title_label)) },
            onValueChange = { tfTitle = it }
        )

        OutlineNumberTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            value = tfAmount,
            label = R.string.add_transaction_amount_label,
            currency = R.string.add_transaction_amount_currency,
            onValueChange = { tfAmount = it }
        )



    }
}

@Composable
@Preview(showBackground = true)
fun AddTransactionPreview() {
    AddTransactionScreen()
}