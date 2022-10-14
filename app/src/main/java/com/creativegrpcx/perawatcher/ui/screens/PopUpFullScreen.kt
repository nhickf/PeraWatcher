package com.creativegrpcx.perawatcher.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.ui.theme.AppTheme



@Composable
fun PopUpFullScreen(
    content: @Composable ColumnScope.() -> Unit = { AddWalletPopUp() }){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )

}

@Composable
fun AddWalletPopUp(){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp),
        imageVector = ImageVector
            .vectorResource(
                id = R.drawable.ic_add_wallet_image
            ),
        contentScale = ContentScale.Fit,
        contentDescription = "Add Wallet"
    )

    Text(
        text = "Oops\n" +
        "Before to proceed adding transaction.\n" +
                "Please add wallet!",
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
    )

    TextButton(
        onClick = {
        },
    ) {
        Text(text = "Proceed")
        Icon(imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Proceed")
    }
}
