package com.creativegrpcx.perawatcher.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.ui.components.ChipVerticalGrid
import com.creativegrpcx.perawatcher.ui.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddTransaction() {

    val titleValue = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Title")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = titleValue.value,
            onValueChange = { value ->
                titleValue.value = value
            },
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = "Put the title here."
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {

                }
            )
        )
        Text(text = "Categories")

        ChipVerticalGrid(
            horizontalSpacing = 8.dp,
            verticalSpacing = 0.dp
        ) {
            CategoryType.values().forEach { category->
                FilterChip(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.wrapContentSize(),
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector
                                .vectorResource(id = Constants.CategoryIcon(category)),
                            contentDescription = "",
                        )
                    },
                    onClick = {

                    },
                    label = {
                        Text(
                            text = category.name,
                            maxLines = 1,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    },
                    selected = false
                )
            }
        }

    }
}