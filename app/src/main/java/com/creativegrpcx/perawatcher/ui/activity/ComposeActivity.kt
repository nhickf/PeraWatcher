package com.creativegrpcx.perawatcher.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.creativegrpcx.perawatcher.MainApplication
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModelFactory
import com.creativegrpcx.perawatcher.ui.components.ApplicationHeader
import com.creativegrpcx.perawatcher.ui.nav.ApplicationRoute
import com.creativegrpcx.perawatcher.ui.theme.AppTheme
import javax.inject.Inject

class ComposeActivity : ComponentActivity() {

    @Inject
    lateinit var globalViewModelFactory: GlobalViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        val viewModel : GlobalViewModel by viewModels {
            globalViewModelFactory
        }

        setContent {
            AppTheme {
                MainScreen(
                    viewModel
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: GlobalViewModel
){
    val navigationController = rememberNavController()
    val currentRoute = viewModel.uiStateRoute.collectAsState().value.newRoute

    // A surface container using the 'background' color from the theme
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            ApplicationHeader(
                newRoute = currentRoute,
            ){
                viewModel.updateCurrentRoute(it)
                navigationController.navigate(it.route)
            }
        }
    ) {
        ApplicationRoute(
            viewModel = viewModel,
            padding =  it,
            navHostController = navigationController
        )
    }
}