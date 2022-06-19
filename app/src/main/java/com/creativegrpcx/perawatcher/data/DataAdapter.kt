package com.creativegrpcx.perawatcher.data

import com.creativegrpcx.perawatcher.dashboard.DashboardItemRecyclerViewAdapter
import com.creativegrpcx.perawatcher.dashboard.DashboardWalletRecyclerViewAdapter

data class DataAdapter(
    var item: DashboardItemRecyclerViewAdapter,
    var wallet: DashboardWalletRecyclerViewAdapter?
)


