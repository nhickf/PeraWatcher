package com.creativegrpcx.perawatcher.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.ui.utils.Constants
import com.creativegrpcx.perawatcher.ui.utils.formatDecimalSeparator


@Composable
@Preview
fun PreviewTransactionItem(){
    TransactionItem(transaction =
    Transaction(
        title = "Compose test",
        category = CategoryType.Bills,
        amount = "122.00",
        date = "2022-08-03",
        time = "14:56:00",
        walletId = "1234"
    )
    )
}

@Composable
fun TransactionItem(
    transaction : Transaction
) {

    Card(
        modifier = Modifier.wrapContentHeight()
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Icon(
                imageVector = ImageVector
                    .vectorResource(id = Constants.categoryIcon(transaction.category)),
                contentDescription = ""
            )

            Column(
                Modifier
                    .wrapContentSize()
                    .weight(1f)
            ) {

                Text(
                    modifier = Modifier.wrapContentHeight(),
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    maxLines = 2,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    text = transaction.title
                )

                Text(
                    modifier = Modifier.wrapContentHeight(),
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    text = transaction.currentDateTime
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textAlign = TextAlign.End,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = transaction.formatAmount
            )
        }
    }
}


