package com.creativegrpcx.perawatcher.ui.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModelFactory
import com.creativegrpcx.perawatcher.ui.screens.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ApplicationRoute(
    factory: GlobalViewModelFactory,
    padding: PaddingValues,
    navHostController: NavHostController
) {

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
        startDestination = NavigationRoute.Dashboard.withoutArgs.route
    ) {
        composable(
            route = NavigationRoute.Dashboard.withoutArgs.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 })
            },
            content = {
                DashboardScreen(
                    viewModel =  viewModel(
                        factory = factory
                    ),
                    navController = navHostController
                )
            },
        )
        composable(
            route = NavigationRoute.Statistics.withoutArgs.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 })
            },
            content = {

            }

        )
        composable(
            route = NavigationRoute.History.withoutArgs.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 })
            },
            content = {
                HistoryScreen(
                    viewModel(
                        factory = factory
                    )
                )
            }
        )
        composable(
            route = NavigationRoute.Wallet.withoutArgs.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 })
            },
            content = {
                WalletScreen(
                    navigation = navHostController,
                    viewModel(
                        factory = factory
                    )
                )
            }
        )
        composable(
            route = NavigationRoute.AddTransaction.withoutArgs.route,
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { 1000 }
                )
            },
            content = {
                AddTransactionScreen(
                    viewModel = viewModel(factory = factory)
                )
            }
        )

        composable(
            route = NavigationRoute.AddWallet.withoutArgs.route,
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { 1000 }
                )
            },
            content = {
                AddWalletScreen(
                    viewModel = viewModel(factory = factory)
                )
            }
        )
//
//        composable(
//            route = NavigationRoute.PopUpAddWallet.withoutArgs.route,
//            enterTransition = {
//                slideInVertically(
//                    initialOffsetY = { 1000 }
//                )
//            },
//            content = {
//                PopUpFullScreen(
//                    viewModel = viewModel,
//                )
//            }
//        )
    }
}
