package com.creativegrpcx.perawatcher.domain.model

import androidx.navigation.NavHostController
import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute

data class RouteState(
    val newRoute : ScreenRoute = NavigationRoute.Dashboard.withoutArgs,
)
