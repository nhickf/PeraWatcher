package com.creativegrpcx.perawatcher.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.creativegrpcx.perawatcher.ui.nav.BottomNavigationBar
import com.creativegrpcx.perawatcher.ui.screens.NavGraphs
import com.creativegrpcx.perawatcher.ui.theme.AppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContent {
                AppTheme {
                    MainScreen()
                }
            }
        } catch (e: Exception) {
            Log.e("error bro", "$e")
        }
    }
}

@Composable
fun MainScreen() {
    val navigationController = rememberNavController()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    // A surface container using the 'background' color from the theme

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            BottomNavigationBar(navController = navigationController)
        }
    ) {
        DestinationsNavHost(
            navController = navigationController,
            navGraph = NavGraphs.root,
            modifier = Modifier.padding(
                top = 16.dp,
                bottom = it.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            )
        )
    }
}