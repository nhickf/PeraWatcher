package com.creativegrpcx.perawatcher

import android.content.Context
import androidx.fragment.app.Fragment
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModelFactory
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var globalViewModelFactory: GlobalViewModelFactory

    override fun onAttach(context: Context) {
        (activity?.application as MainApplication).component.inject(this)
        super.onAttach(context)
    }

}