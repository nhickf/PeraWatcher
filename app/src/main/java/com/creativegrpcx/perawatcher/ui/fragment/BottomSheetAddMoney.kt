package com.creativegrpcx.perawatcher.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creativegrpcx.perawatcher.databinding.BottomSheetAddMoneyLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


private var _binding : BottomSheetAddMoneyLayoutBinding? = null
private val binding get() = _binding!!

class BottomSheetAddMoney  : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetAddMoneyLayoutBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
