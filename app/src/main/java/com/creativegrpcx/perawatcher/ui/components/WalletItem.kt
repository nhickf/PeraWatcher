package com.creativegrpcx.perawatcher.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.types.WalletType
import com.creativegrpcx.perawatcher.ui.shared.TextIcon
import com.creativegrpcx.perawatcher.ui.utils.formatDecimalSeparator

@Composable
fun WalletItem(
    wallet : Wallet = Wallet(
        "Savings",
        "21,300",
        walletType = WalletType.CASH,
        true,
        isPrimary = false,
        walletId = "132"
    )
) {

    Card(
        Modifier.wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ){
        Column(
            Modifier.padding(16.dp),
        ) {

            TextIcon(
                text = wallet.walletName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_chevron_right_24))

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                text = "Available Balance")

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                text = wallet.formatWalletAmount
            )
        }
    }


}

@Preview
@Composable
fun PreviewWalletItem (){
    WalletItem()
}
