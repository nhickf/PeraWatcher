package com.creativegrpcx.perawatcher.domain.controller

import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class UpdateRoute {

    operator fun invoke( routes : StateFlow<List<ScreenRoute>> ,newRoute : ScreenRoute ) : Flow<List<ScreenRoute>> {
       return routes.map{ oldRoutes ->
           return@map oldRoutes.map { oldRoute ->
                if (oldRoute == newRoute){
                    newRoute.apply {
                        this.isSelected = true
                    }
                }
                oldRoute.apply {
                    this.isSelected = false
                }
            }
        }
    }

}
