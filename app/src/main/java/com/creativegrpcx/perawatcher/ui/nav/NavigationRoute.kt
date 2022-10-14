package com.creativegrpcx.perawatcher.ui.nav

sealed class NavigationRoute(
    private val route: ScreenRoute
) {
    object Dashboard : NavigationRoute(ScreenRoute( route = "dashboard", name = "dashboard", isSelected = true))
    object Statistics : NavigationRoute(ScreenRoute( route = "statistics", name = "statistics"))
    object History : NavigationRoute(ScreenRoute( route = "history", name = "history"))
    object Wallet : NavigationRoute(ScreenRoute( route = "wallet", name = "wallet"))
    object AddTransaction : NavigationRoute(ScreenRoute( route = "add_trans", name = "add_trans" , isPopInclusive = true , popUpTo = "dashboard"))
    object AddWallet : NavigationRoute(ScreenRoute( route = "add_wallet", name = "add_wallet", isPopInclusive = true , popUpTo = "wallet"))
    object PopUpAddWallet : NavigationRoute(ScreenRoute( route = "pop_up_add_wallet", name = "pop_up_add_wallet", isPopUp = true))

    fun withArgs(vararg args : Any): ScreenRoute{
        return route.copy(
            args = listOf(args)
        )
    }


    val withoutArgs = route
}

data class ScreenRoute(
    val route : String,
    val name : String,
    val args : List<Any>? = null,
    val isSelected : Boolean = false,
    val isPopUp : Boolean = false,
    val isPopInclusive : Boolean = false,
    val popUpTo : String = ""
)