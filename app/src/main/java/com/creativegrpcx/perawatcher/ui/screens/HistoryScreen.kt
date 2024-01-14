package com.creativegrpcx.perawatcher.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.creativegrpcx.perawatcher.ui.components.ActivityItem
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.viewmodel.HistoryViewModel
import com.creativegrpcx.perawatcher.ui.components.ExpensesItem
import com.creativegrpcx.perawatcher.ui.components.TransactionItemHeader
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen(
    id: Int = 3,
) {
    val viewModel: HistoryViewModel = hiltViewModel()

    Column {
        TabSection()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                top = 8.dp,
                bottom = 8.dp
            )
        )
        {

            item {
                TransactionItemHeader()
            }

            items(2) {
                ExpensesItem(
                    transaction = Transaction(
                        title = "Compose test",
                        category = CategoryType.Bills,
                        amount = "122.00",
                        date = "2022-08-03",
                        time = "14:56:00",
                        walletId = "1234"
                    )
                )
            }

            item {
                TransactionItemHeader()
            }

            items(5) {
                ExpensesItem(
                    transaction = Transaction(
                        title = "Compose test",
                        category = CategoryType.Bills,
                        amount = "122.00",
                        date = "2022-08-03",
                        time = "14:56:00",
                        walletId = "1234"
                    )
                )
            }

            item {
                TransactionItemHeader()
            }

            items(3) {
                ExpensesItem(
                    transaction = Transaction(
                        title = "Compose test",
                        category = CategoryType.Bills,
                        amount = "122.00",
                        date = "2022-08-03",
                        time = "14:56:00",
                        walletId = "1234"
                    )
                )
            }
        }
     }

}

@Composable
private fun TabSection() {
    val tabs = listOf("Expenses", "Savings")
    var tabsState by remember { mutableIntStateOf(0) }

    TabRow(
        selectedTabIndex = tabsState,
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = index == tabsState,
                onClick = { tabsState = index },
                text = { Text(text = title) }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultHistoryScreen() {
    TabSection()
}