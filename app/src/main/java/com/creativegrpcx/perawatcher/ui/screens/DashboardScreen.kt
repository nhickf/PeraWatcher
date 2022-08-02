package com.creativegrpcx.perawatcher.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.ui.components.TransactionItem
import com.creativegrpcx.perawatcher.ui.components.WalletItem
import com.creativegrpcx.perawatcher.ui.theme.AppTheme


@Composable
fun DashboardScreen(
    viewModel: GlobalViewModel?
){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Today Expenses",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "$20,000",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        )
        {

            items(5){
                TransactionItem()
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
