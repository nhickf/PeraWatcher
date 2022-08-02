package com.creativegrpcx.perawatcher.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.ui.screens.DashboardScreen
import com.creativegrpcx.perawatcher.ui.screens.HistoryScreen
import com.creativegrpcx.perawatcher.ui.screens.WalletScreen

@Composable
fun ApplicationRoute(
    viewModel : GlobalViewModel,
    padding : PaddingValues,
    navHostController: NavHostController
){


    NavHost(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp , top = padding.calculateTopPadding(), end = 16.dp))
            .fillMaxSize(),
        navController = navHostController,
        startDestination = NavigationRoute.Dashboard.withoutArgs.route){
        composable(
            route = NavigationRoute.Dashboard.withoutArgs.route,
            content = {
                DashboardScreen(viewModel)
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
                HistoryScreen(viewModel)
            }
        )
        composable(
            route = NavigationRoute.Wallet.withoutArgs.route,
            content = {
                WalletScreen(viewModel)
            }
        )
    }

}
