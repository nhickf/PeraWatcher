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
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.ui.shared.TextIcon


@Composable
@Preview
fun TransactionItem() {

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

            //ImageVector.vectorResource(id = R.drawable.ic_baseline_chevron_right_24)

            Icon(
                imageVector = ImageVector
                    .vectorResource(id = R.drawable.baseline_health_and_safety_24),
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
                    text = "Available Balaaaance"
                )

                Text(
                    modifier = Modifier.wrapContentHeight(),
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    text = "Jan 10 2022 11:20 AM"
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth().weight(1f),
                textAlign = TextAlign.End,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = "$112.60"
            )
        }
    }
}

