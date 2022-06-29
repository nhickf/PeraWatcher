package com.creativegrpcx.perawatcher

import android.os.Bundle
import android.text.Editable
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModelFactory
import dagger.Provides
import java.security.Permission
import javax.inject.Inject
import javax.inject.Singleton

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