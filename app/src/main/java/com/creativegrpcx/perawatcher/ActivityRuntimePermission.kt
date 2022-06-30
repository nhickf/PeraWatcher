package com.creativegrpcx.perawatcher

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ActivityRuntimePermission {

    fun checkSelfPermission(context: Context, activity: Activity ,permission : String , permissionCallback : ActivityRuntimePermissionCallback) {
        when {
            //Check if permission is already granted
            ContextCompat.checkSelfPermission(context ,permission) == PackageManager.PERMISSION_GRANTED -> {
                permissionCallback.onPermissionAlreadyGranted()
            }

            //Check if should show rationale then launch permission request
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                permission
            ) -> {
                Log.e("checkSelfPermission", "Show permission Rationale")
                permissionCallback.onPermissionShowRationale()
            }
            //permission not yet granted and no need to show rationale
            else -> {
                permissionCallback.onPermissionDenied()
            }
        }
    }

}

interface ActivityRuntimePermissionCallback {

   fun onPermissionGranted(permission: String)
   fun onPermissionAlreadyGranted()
   fun onPermissionDenied()
   fun onPermissionShowRationale()

}