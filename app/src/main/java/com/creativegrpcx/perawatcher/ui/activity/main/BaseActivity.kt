package com.creativegrpcx.perawatcher.ui.activity.main

import android.os.Bundle
import android.text.Editable
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.creativegrpcx.perawatcher.ui.utils.ActivityRuntimePermission
import com.creativegrpcx.perawatcher.ui.utils.ActivityRuntimePermissionCallback
import com.creativegrpcx.perawatcher.MainApplication
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModelFactory
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject lateinit var globalViewModelFactory: GlobalViewModelFactory

    private val activityRuntimePermission = ActivityRuntimePermission()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).component.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun editable(text :String) : Editable {
        return Editable.Factory.getInstance().newEditable(text)
    }

    fun checkRequestedPermission(permission: String, permissionCallback: ActivityRuntimePermissionCallback) {

        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted){
                permissionCallback.onPermissionDenied()
            }else{
                permissionCallback.onPermissionGranted(permission)
            }
        }

        val inLinePermissionCallback = object  : ActivityRuntimePermissionCallback {
            override fun onPermissionGranted(permission: String) {
                permissionCallback.onPermissionGranted(permission)
            }

            override fun onPermissionAlreadyGranted() {
                permissionCallback.onPermissionAlreadyGranted()
            }

            override fun onPermissionDenied() {
                requestPermissionLauncher.launch(permission)
            }

            override fun onPermissionShowRationale() {
                requestPermissionLauncher.launch(permission)
            }

        }

        activityRuntimePermission.checkSelfPermission(this,this , permission, inLinePermissionCallback)
    }
}
