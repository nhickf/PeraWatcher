package com.creativegrpcx.perawatcher.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.creativegrpcx.perawatcher.MainApplication
import com.creativegrpcx.perawatcher.domain.viewmodel.AddTransactionViewModel
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
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
        val globalViewModel: GlobalViewModel by viewModels {
            globalViewModelFactory
        }

        setContent {
            AppTheme {
                MainScreen(
                    globalViewModel
                )
            }
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

    // A surface container using the 'background' color from the theme

    if (state.isRouteChange) navigationController.navigate(state.currentRoute.route)
    if (state.isNavigateUp) navigationController.navigateUp()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            ApplicationHeader(
                newRoute = state.currentRoute,
            ) {
                globalViewModel.updateCurrentRoute(it)
            }

        }
    ) {
        ApplicationRoute(
            viewModel = globalViewModel,
            padding = it,
            navHostController = navigationController
        )
    }
}