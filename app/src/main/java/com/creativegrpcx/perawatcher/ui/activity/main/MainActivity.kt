package com.creativegrpcx.perawatcher.ui.activity.main

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.creativegrpcx.perawatcher.ui.utils.ActivityRuntimePermissionCallback
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.databinding.ActivityMainBinding
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel

private lateinit var binding : ActivityMainBinding

class MainActivity : BaseActivity() {

    private val globalViewModel: GlobalViewModel by viewModels {
        globalViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.mainBottomNavigation,navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("addOnDestinationChangedListener",destination.displayName)

            when(destination.id) {
                R.id.dashboardFragment -> {
                    globalViewModel.loadTransactions()
                    globalViewModel.loadWallet()
                }
                R.id.historyFragment -> {
                    globalViewModel.loadTransactions()
                }
                R.id.statisticsFragment -> {
                    globalViewModel.loadTransactions()
                }
                R.id.walletFragment -> {
                    globalViewModel.loadWallet()
                }
            }

        }

        checkRequestedPermission(Manifest.permission.READ_EXTERNAL_STORAGE, object :
            ActivityRuntimePermissionCallback {
            override fun onPermissionGranted(permission: String) {
                // do nothing
                Log.d("checkRequestedPermission", "onPermissionAlreadyGranted")
            }

            override fun onPermissionAlreadyGranted() {
                // do nothing
                Log.d("checkRequestedPermission", "onPermissionAlreadyGranted")
            }

            override fun onPermissionDenied() {
                Log.d("checkRequestedPermission", "onPermissionDenied")
            }

            override fun onPermissionShowRationale() {
                Log.d("checkRequestedPermission", "onPermissionShowRationale")
            }

        })
    }

}
