package com.creativegrpcx.perawatcher.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.ui.components.WalletItem
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.theme.AppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.creativegrpcx.perawatcher.ui.utils.formatDecimalSeparator

@Composable
fun WalletScreen(
    viewModel: GlobalViewModel = viewModel()
) {

    val state = viewModel.walletState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Net Worth",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                text = "$${state.value.totalNetWorth.formatDecimalSeparator()}",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = {
                viewModel.updateCurrentRoute(NavigationRoute.AddWallet.withoutArgs)
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

            items(state.value.wallets){
                WalletItem(it)
            }

        }
    }

}

@Composable
@Preview(showBackground = true)
fun DefaultWalletScreen(){
    AppTheme() {
        WalletScreen()
    }

}
