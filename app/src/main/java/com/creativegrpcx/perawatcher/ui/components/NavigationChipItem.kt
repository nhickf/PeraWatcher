package com.creativegrpcx.perawatcher.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute
import com.creativegrpcx.perawatcher.ui.utils.Constants

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationChipItem(
    routeContent : ScreenRoute = Constants.MainScreenRoutes[0],
    currentRoute : ScreenRoute = Constants.MainScreenRoutes[0],
    onClick : (route : ScreenRoute) -> Unit = {}
) {

    ElevatedFilterChip(
        shape = RoundedCornerShape(16.dp),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.Transparent,
        ),
        onClick = {
            onClick(routeContent)
        },
        label = {
            Text(
                text = routeContent.name.capitalize(),
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        },
        selected = currentRoute == routeContent
    )


}
