package com.creativegrpcx.perawatcher.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.utils.Constants
import com.creativegrpcx.perawatcher.ui.utils.currentRoute

@Composable
fun ApplicationHeader(
    navigation: NavController,
) {

    val navigationState by navigation.currentBackStackEntryAsState()
    val latestRoute = navigationState?.destination?.route.currentRoute()

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(PaddingValues(top = 8.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {

        when (latestRoute.route) {
            NavigationRoute.AddTransaction.withoutArgs.route,
            NavigationRoute.AddWallet.withoutArgs.route -> {
                IconButton(onClick = {
                    navigation.navigateUp()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }
            NavigationRoute.PopUpAddWallet.withoutArgs.route -> {
            }
            else -> {
                IconButton(onClick = {

                }) {

                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(start = 16.dp, end = 8.dp),
                )
                {
                    items(Constants.MainScreenRoutes) { route ->
                        NavigationChipItem(
                            routeContent = route,
                            currentRoute = latestRoute,
                        ) {
                            navigation.navigate(it.route) {
                                launchSingleTop = true
                            }
                        }
                    }
                }
            }
        }
    }
}
