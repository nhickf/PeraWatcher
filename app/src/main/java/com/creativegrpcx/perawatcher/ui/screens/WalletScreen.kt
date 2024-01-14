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
import androidx.hilt.navigation.compose.hiltViewModel
import com.creativegrpcx.perawatcher.ui.components.WalletItem
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.creativegrpcx.perawatcher.domain.viewmodel.WalletViewModel
import com.creativegrpcx.perawatcher.ui.screens.destinations.AddWalletScreenDestination
import com.creativegrpcx.perawatcher.ui.utils.formatDecimalSeparator
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun WalletScreen(
    id: Int = 4,
    navigation: DestinationsNavigator,
) {

    val viewModel: WalletViewModel = hiltViewModel()

    val state = viewModel.walletState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        )
        {

            item {
              HeaderSection{
                  navigation.navigate(direction = AddWalletScreenDestination())
              }
            }

            items(3) {
                WalletItem()
            }
        }
    }
}

@Composable
private fun HeaderSection(
    onAddWalletClick: () -> Unit
) {
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
            text = "Add new wallet",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        IconButton(onClick = onAddWalletClick) {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
        }
    }
}

@Composable
@Preview
fun WalletPreview() {
    HeaderSection({})
}

