package com.creativegrpcx.perawatcher.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        val viewModel : GlobalViewModel by viewModels {
            globalViewModelFactory
        }

        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Log.e("ComposeActivity","${viewModel.uiStateRoute.collectAsState().value}")
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = {
                        ApplicationHeader(
                            routes = viewModel.uiStateRoute.collectAsState().value
                        ){
                            viewModel.updateCurrentRoute(it)
                        }
                    }
                ) {
                    ApplicationRoute(it)
                }
            }
        }
    }
}
