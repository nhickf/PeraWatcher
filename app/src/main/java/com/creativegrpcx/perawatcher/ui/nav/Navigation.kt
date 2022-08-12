package com.creativegrpcx.perawatcher.ui.nav

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.ui.screens.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ApplicationRoute(
    viewModel : GlobalViewModel,
    padding : PaddingValues,
    navHostController: NavHostController
){

    AnimatedNavHost(
        modifier = Modifier
            .padding(
                PaddingValues(
                    start = 16.dp,
                    top = padding.calculateTopPadding(),
                    end = 16.dp
                )
            )
            .fillMaxSize(),
        navController = navHostController,
        startDestination = NavigationRoute.Dashboard.withoutArgs.route){
        composable(
            route = NavigationRoute.Dashboard.withoutArgs.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 })
            },
            content = {
                DashboardScreen(viewModel)
            },
        )
        composable(
            route = NavigationRoute.Statistics.withoutArgs.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 })
            },
            content = {
                StatisticScreen()
            }

        )
        composable(
            route = NavigationRoute.History.withoutArgs.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 })
            },
            content = {
                HistoryScreen(viewModel)
            }
        )
        composable(
            route = NavigationRoute.Wallet.withoutArgs.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 })
            },
            content = {
                WalletScreen(viewModel)
            }
        )
        composable(
            route = NavigationRoute.AddTransaction.withoutArgs.route,
            enterTransition = {
                slideInVertically (
                    initialOffsetY = { 1000 }
                )
            },
            content = {
                AddTransactionScreen(
                    viewModel =  viewModel
                )
            }
        )

        composable(
            route = NavigationRoute.AddWallet.withoutArgs.route,
            enterTransition = {
                slideInVertically (
                    initialOffsetY = { 1000 }
                )
            },
            content = {
                AddWalletScreen(
                    viewModel =  viewModel
                )
            }
        )

        composable(
            route = NavigationRoute.PopUpAddWallet.withoutArgs.route,
            enterTransition = {
                slideInVertically (
                    initialOffsetY = { 1000 }
                )
            },
            content = {
               PopUpFullScreen (
                   viewModel =  viewModel,
                )
            }
        )

    }

    BackHandler {
        viewModel.updateCurrentRoute(null)
    }

}
