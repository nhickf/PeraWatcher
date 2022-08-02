package com.creativegrpcx.perawatcher.ui.utils

import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute

object Constants {

    val ScreenRoutes = listOf(
        ScreenRoute( route = "dashboard", name = "dashboard").apply { isSelected = true },
        ScreenRoute( route = "statistics", name = "statistics"),
        ScreenRoute( route = "history", name = "history"),
        ScreenRoute( route = "wallet", name = "wallet"),
    )

}
