package com.creativegrpcx.perawatcher.ui.utils

import androidx.navigation.Navigation
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute
import java.text.NumberFormat
import java.util.*

fun String.formatDecimalSeparator(): String {
    return if (toString().isEmpty() || toString().isBlank()) toString() else NumberFormat.getNumberInstance(
        Locale.US
    ).format(toString().toDouble())
}

fun String.removeComma(): Double {
    return if (toString().isEmpty() || toString().isBlank()) 0.00 else toString().replace(",", "")
        .trim().toDouble()
}

fun String?.currentRoute(): ScreenRoute {

    return when (this) {
        NavigationRoute.Statistics.withoutArgs.route -> NavigationRoute.Statistics.withoutArgs
        NavigationRoute.History.withoutArgs.route -> NavigationRoute.History.withoutArgs
        NavigationRoute.Wallet.withoutArgs.route -> NavigationRoute.Wallet.withoutArgs
        NavigationRoute.AddTransaction.withoutArgs.route -> NavigationRoute.AddTransaction.withoutArgs
        NavigationRoute.AddWallet.withoutArgs.route -> NavigationRoute.AddWallet.withoutArgs
        NavigationRoute.PopUpAddWallet.withoutArgs.route -> NavigationRoute.PopUpAddWallet.withoutArgs
        else -> NavigationRoute.Dashboard.withoutArgs
    }
}
