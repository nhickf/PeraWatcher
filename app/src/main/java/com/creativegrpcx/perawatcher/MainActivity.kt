package com.creativegrpcx.perawatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.creativegrpcx.perawatcher.databinding.ActivityMainBinding
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModel

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
    }

}