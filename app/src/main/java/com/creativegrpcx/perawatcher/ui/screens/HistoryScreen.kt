package com.creativegrpcx.perawatcher.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.ui.components.TransactionItem
import com.creativegrpcx.perawatcher.ui.shared.TextIcon
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.ui.theme.AppTheme
import com.creativegrpcx.perawatcher.ui.utils.formatDecimalSeparator
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen(
    viewModel: GlobalViewModel?
) {
//    viewModel?.loadHistoryScreen()

    val state = viewModel?.historyState?.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Overall Expenses",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "$${state?.value?.overAllExpenses?.formatDecimalSeparator()}",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        )
        {

            state?.value?.groupTransactions?.let {
                it.forEach { (category, list) ->

                    stickyHeader {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            TextIcon(
                                text = category.capitalize(),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                icon = ImageVector
                                    .vectorResource(id = R.drawable.ic_baseline_more_horiz_24)

                            ) {

                            }
                        }
                    }

                    items(list) { transaction ->
                        TransactionItem(
                            transaction = transaction
                        )
                    }
                }

            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun DefaultHistoryScreen() {
    AppTheme() {
        HistoryScreen(viewModel = null)
    }
}