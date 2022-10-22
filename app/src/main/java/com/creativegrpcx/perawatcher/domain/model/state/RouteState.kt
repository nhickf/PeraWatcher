package com.creativegrpcx.perawatcher.domain.model.state

import com.creativegrpcx.perawatcher.ui.nav.NavigationRoute
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute

data class RouteState(
    val currentRoute: ScreenRoute = NavigationRoute.Dashboard.withoutArgs,
    val oldRoute: ScreenRoute? = null,
    val isRouteChange: Boolean = false,
    override val isNavigateUp: Boolean = false,
) : MainState()
