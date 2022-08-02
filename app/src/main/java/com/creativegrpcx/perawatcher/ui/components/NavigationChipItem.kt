package com.creativegrpcx.perawatcher.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
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
    route : ScreenRoute = Constants.ScreenRoutes[0],
    onClick : (route : ScreenRoute) -> Unit = {}
) {

    ElevatedFilterChip(
        shape = RoundedCornerShape(16.dp),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.Transparent,
        ),
        onClick = {
            onClick(route)
        },
        label = {
            Text(
                text = route.name.capitalize(),
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        },
        selected = route.isSelected
    )


}
