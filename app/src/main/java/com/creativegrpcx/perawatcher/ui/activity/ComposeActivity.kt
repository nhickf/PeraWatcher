package com.creativegrpcx.perawatcher.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.creativegrpcx.perawatcher.MainApplication
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModelFactory
import com.creativegrpcx.perawatcher.ui.components.ApplicationHeader
import com.creativegrpcx.perawatcher.ui.nav.ApplicationRoute
import com.creativegrpcx.perawatcher.ui.theme.AppTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import javax.inject.Inject

class ComposeActivity : ComponentActivity() {

    @Inject
    lateinit var globalViewModelFactory: GlobalViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).component.inject(this)
        super.onCreate(savedInstanceState)

        try {
            setContent {
                AppTheme {
                    MainScreen(
                        globalViewModelFactory
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("error bro", "$e")
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    factory: GlobalViewModelFactory,
) {
    val navigationController = rememberAnimatedNavController()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    // A surface container using the 'background' color from the theme

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            ApplicationHeader(
                navigation = navigationController,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        ApplicationRoute(
            factory = factory,
            padding = it,
            navHostController = navigationController
        )
    }
}