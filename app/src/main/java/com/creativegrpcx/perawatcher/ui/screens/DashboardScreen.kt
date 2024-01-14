package com.creativegrpcx.perawatcher.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.viewmodel.DashboardViewModel
import com.creativegrpcx.perawatcher.ui.components.ActivityCardItem
import com.creativegrpcx.perawatcher.ui.components.ActivityItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@OptIn(ExperimentalFoundationApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun DashboardScreen(
    id: Int = 1,
    navController: DestinationsNavigator
) {

    val viewModel: DashboardViewModel = hiltViewModel()
    val state by viewModel.dashBoardState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        item {
            HeaderSection()
        }

        item {
            Text(
                text = "Insights",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                items(2) {
                    ActivityCardItem()
                }
            }
        }

        item {
            Text(
                text = "Transaction",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }

        items(10) {
            ActivityItem(
                Transaction(
                    title = "Sample ${it}",
                    amount = "122.00",
                    date = "2022-08-03",
                    time = "14:56:00",
                    walletId = "1234",
                    category = CategoryType.Appliances
                )
            )
        }

    }
}

@Composable
private fun HeaderSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    )
                ) {
                    append("Sat, 25 July\n")
                }
                append("Good Day,\n")
                append("Nhick.")
            },

            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultDashboardScreen() {
    HeaderSection()
}
