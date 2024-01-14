package com.creativegrpcx.perawatcher.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


@Composable
@Preview
fun PreviewActivityItem() {
    ActivityItem(
        transaction =
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
@Preview
fun PreviewExpensesItem() {
    ExpensesItem(
        transaction =
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
@Preview
fun PreviewSavingsItem() {
    SavingsItem(
        transaction =
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
fun ActivityItem(
    transaction: Transaction
) {

    Card(
        modifier = Modifier.wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
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

@Composable
fun ExpensesItem(
    transaction: Transaction
) {

    Card(
        modifier = Modifier.wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
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
                contentDescription = "",
            )

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                maxLines = 2,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                text = transaction.title
            )

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                textAlign = TextAlign.End,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = "- ${transaction.formatAmount}"
            )
        }
    }
}

@Composable
fun SavingsItem(
    transaction: Transaction
) {

    Card(
        modifier = Modifier.wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
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
                contentDescription = "",
            )

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                maxLines = 2,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                text = transaction.title
            )

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                textAlign = TextAlign.End,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = transaction.formatAmount
            )
        }
    }
}

@Preview
@Composable
fun TransactionItemHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Today",
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "- $10,000",
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Normal
        )
    }
}