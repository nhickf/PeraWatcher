package com.creativegrpcx.perawatcher.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.ui.components.TransactionItem
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.theme.AppTheme


@Composable
fun DashboardScreen(
    viewModel: GlobalViewModel?,
){
    val state = viewModel?.dashBoardState?.collectAsState()

    Log.e("state", "$state")

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {

        Button(onClick = {
            viewModel?.updateCurrentRoute(NavigationRoute.AddTransaction.withoutArgs)
        }) {
            Text(text = "add transaction")
        }
        Text(
            text = "Today Expenses",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = "$${state?.value?.todayExpenses}",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        )
        {

            state?.let {
                Log.e("Items", "${it.value.transactions}")

                items(it.value.transactions){ item ->
                    TransactionItem(
                        transaction = item
                    )
                }
            }

        }
    }

}
@Composable
@Preview(showBackground = true)
fun DefaultDashboardScreen(){
    AppTheme() {
        DashboardScreen(null)
    }

}
