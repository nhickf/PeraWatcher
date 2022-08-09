package com.creativegrpcx.perawatcher.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.ui.components.ChipVerticalGrid
import com.creativegrpcx.perawatcher.ui.utils.Constants
import com.creativegrpcx.perawatcher.domain.types.WalletType
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.creativegrpcx.perawatcher.domain.utils.AddWalletEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun AddWalletScreen(
    viewModel: GlobalViewModel = viewModel()
) {

    val scrollState = rememberScrollState()
    val state = viewModel.addWalletState.collectAsState()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
            .verticalScroll(
                state = scrollState,
                enabled = true
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        Text(text = "Title")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.walletName,
            label = {
                TextFieldTextPlaceHolder(text = "Put the title here")
            },
            onValueChange = {
                viewModel.onAddWalletEventHandler(AddWalletEvent.TitleChange(it))
            },

            colors = TextFieldDefaults.outlinedTextFieldColors(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

        Text(text = "Type")

        ChipVerticalGrid(
            horizontalSpacing = 8.dp,
            verticalSpacing = 0.dp
        ) {
            WalletType.values().forEach { wallet ->
                FilterChip(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.wrapContentSize(),
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector
                                .vectorResource(id = Constants.walletIcon(wallet)),
                            contentDescription = "",
                        )
                    },
                    onClick = {
                        viewModel.onAddWalletEventHandler(AddWalletEvent.TypeChange(wallet))
                    },
                    label = {
                        Text(
                            text = wallet.name,
                            maxLines = 1,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    },
                    selected = state.value.walletType == wallet
                )
            }
        }

        Text(text = "Initial Amount")

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.walletAmount,
            onValueChange = { value ->
                viewModel.onAddWalletEventHandler(AddWalletEvent.AmountChange(value))
            },
            label = {
                TextFieldTextPlaceHolder(text = "Put the amount here")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Decimal
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    viewModel.onAddWalletEventHandler(AddWalletEvent.SaveWallet{
                        viewModel.updateCurrentRoute(null)
                    })
                }
            )
        )

        FilledIconButton(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onClick = {
                viewModel.onAddWalletEventHandler(AddWalletEvent.SaveWallet{
                    viewModel.updateCurrentRoute(null)
                })
            }) {
            Text(text = "Press to save your transaction")
        }

    }

    BackHandler {
        viewModel.updateCurrentRoute(null)
    }
}