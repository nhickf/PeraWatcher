package com.creativegrpcx.perawatcher.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute
import com.creativegrpcx.perawatcher.ui.utils.Constants

@Composable
fun ApplicationHeader(
    routes: List<ScreenRoute>,
    onRouteChange: (route: ScreenRoute) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(PaddingValues(top = 8.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        IconButton(onClick = {
            onRouteChange(
                ScreenRoute(
                    name = "profile",
                    route = "profile"
                )
            )
        }) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 8.dp),
        )
        {
            items(routes) { route ->
                NavigationChipItem(route) {
                    onRouteChange(it)
                }
            }
        }
    }
}
