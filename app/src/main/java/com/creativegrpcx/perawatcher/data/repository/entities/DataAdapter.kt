package com.creativegrpcx.perawatcher.data.repository.entities

import com.creativegrpcx.perawatcher.ui.fragment.dashboard.DashboardItemRecyclerViewAdapter
import com.creativegrpcx.perawatcher.ui.fragment.dashboard.DashboardWalletRecyclerViewAdapter

data class DataAdapter(
    var item: DashboardItemRecyclerViewAdapter,
    var wallet: DashboardWalletRecyclerViewAdapter?
)
