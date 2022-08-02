package com.creativegrpcx.perawatcher.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.creativegrpcx.perawatcher.ui.screens.DashboardScreen
import com.creativegrpcx.perawatcher.ui.screens.WalletScreen

@Composable
fun ApplicationRoute(
    padding : PaddingValues
){

    val navigationController = rememberNavController()

    NavHost(
        modifier = Modifier.padding(PaddingValues(start = 16.dp , top = padding.calculateTopPadding(), end = 16.dp)),
        navController = navigationController,
        startDestination = NavigationRoute.Dashboard.withoutArgs.route){
        composable(
            route = NavigationRoute.Dashboard.withoutArgs.route,
            content = {
                DashboardScreen()
            }
        )
        composable(
            route = NavigationRoute.Statistics.withoutArgs.route,
            content = {

            }
        )
        composable(
            route = NavigationRoute.History.withoutArgs.route,
            content = {

            }
        )
        composable(
            route = NavigationRoute.Wallet.withoutArgs.route,
            content = {
                WalletScreen()
            }
        )
    }

}
