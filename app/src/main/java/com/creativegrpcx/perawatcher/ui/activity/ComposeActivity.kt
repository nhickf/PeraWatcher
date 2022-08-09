package com.creativegrpcx.perawatcher.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.creativegrpcx.perawatcher.MainApplication
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModelFactory
import com.creativegrpcx.perawatcher.ui.components.ApplicationHeader
import com.creativegrpcx.perawatcher.ui.nav.ApplicationRoute
import com.creativegrpcx.perawatcher.ui.theme.AppTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.flow.collectLatest
import java.util.*
import javax.inject.Inject

class ComposeActivity : ComponentActivity() {

    @Inject
    lateinit var globalViewModelFactory: GlobalViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        val globalViewModel: GlobalViewModel by viewModels {
            globalViewModelFactory
        }
        try {
            setContent {
                AppTheme {
                    MainScreen(
                        globalViewModel
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
    globalViewModel: GlobalViewModel,
) {
    val navigationController = rememberAnimatedNavController()
    val state = globalViewModel.routeState.collectAsState().value
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    // A surface container using the 'background' color from the theme

    if (state.isRouteChange) navigationController.navigate(state.currentRoute.route) {
        launchSingleTop = true
        if (state.oldRoute != null) popUpTo(state.oldRoute.route)

    }
    if (state.isNavigateUp) navigationController.navigateUp()


    LaunchedEffect(Unit) {
        globalViewModel.errorState.collectLatest {
            if (it.isShowed) {
                snackbarHostState.showSnackbar(
                    message = it.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            ApplicationHeader(
                newRoute = state.currentRoute,
            ) {
                globalViewModel.updateCurrentRoute(it)
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        ApplicationRoute(
            viewModel = globalViewModel,
            padding = it,
            navHostController = navigationController
        )
    }
}