package com.creativegrpcx.perawatcher.ui.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.ui.screens.NavGraphs
import com.creativegrpcx.perawatcher.ui.screens.appCurrentDestinationAsState
import com.creativegrpcx.perawatcher.ui.screens.destinations.DashboardScreenDestination
import com.creativegrpcx.perawatcher.ui.screens.destinations.Destination
import com.creativegrpcx.perawatcher.ui.screens.destinations.HistoryScreenDestination
import com.creativegrpcx.perawatcher.ui.screens.destinations.StatisticsScreenDestination
import com.creativegrpcx.perawatcher.ui.screens.destinations.WalletScreenDestination
import com.creativegrpcx.perawatcher.ui.screens.startAppDestination

enum class BottomNavDestination(
    val direction: Destination,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    Dashboard(
        direction = DashboardScreenDestination,
        icon = R.drawable.ic_baseline_dashboard_24,
        label = R.string.bottom_nav_label_dashboard
    ),

    Graph(
        direction = StatisticsScreenDestination,
        icon = R.drawable.ic_baseline_show_chart_24,
        label = R.string.bottom_nav_label_stats
    ),

    History(
        direction = HistoryScreenDestination,
        icon = R.drawable.ic_baseline_list_24,
        label = R.string.bottom_nav_label_history
    ),

    Wallet(
        direction = WalletScreenDestination,
        icon = R.drawable.ic_baseline_account_balance_wallet_24,
        label = R.string.bottom_nav_label_wallet
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val appCurrentDestinationAsState = navController.appCurrentDestinationAsState()
    val currentDestination: Destination = appCurrentDestinationAsState.value
        ?: NavGraphs.root.startAppDestination
    val showNavBar =
        currentDestination in BottomNavDestination.entries.map { it.direction }

    if (showNavBar) {
        NavigationBar {
            BottomNavDestination.entries.forEach { des ->
                NavigationBarItem(
                    selected = currentDestination == des.direction,
                    onClick = {
                        navController.navigate(
                            route = des.direction.route,
                            navOptions = navOptions {
                                launchSingleTop = true
                            })

                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = des.icon),
                            contentDescription = null
                        )
                    },
                    label = { Text(stringResource(des.label)) }
                )
            }
        }
    }
}