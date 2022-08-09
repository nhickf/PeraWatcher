package com.creativegrpcx.perawatcher.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute
import com.creativegrpcx.perawatcher.ui.theme.AppTheme
import com.creativegrpcx.perawatcher.ui.utils.formatDecimalSeparator


@Composable
fun DashboardScreen(
    viewModel: GlobalViewModel?,
){
    val state = viewModel?.dashBoardState?.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {

        Text(
            text = "Today Expenses",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
        )

        Row(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f).wrapContentHeight(),
                text = "$${state?.value?.todayExpenses?.formatDecimalSeparator()}",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = {
                viewModel?.updateCurrentRoute(NavigationRoute.AddTransaction.withoutArgs)
            }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        )
        {

            state?.let {
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
