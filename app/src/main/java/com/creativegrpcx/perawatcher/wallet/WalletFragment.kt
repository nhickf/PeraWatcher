package com.creativegrpcx.perawatcher.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.creativegrpcx.perawatcher.BaseFragment
import com.creativegrpcx.perawatcher.MainApplication
import com.creativegrpcx.perawatcher.MarginItemDecoration
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.dashboard.DashboardWalletRecyclerViewAdapter
import com.creativegrpcx.perawatcher.databinding.FragmentWalletBinding
import com.creativegrpcx.perawatcher.repository.entities.Wallet
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private var _binding : FragmentWalletBinding? = null
private val binding get() = _binding!!
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val adapter by lazy {
    DashboardWalletRecyclerViewAdapter()
}

/**
 * A simple [Fragment] subclass.
 * Use the [WalletFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WalletFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val globalViewModel: GlobalViewModel by viewModels {
        globalViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        globalViewModel.loadWallet()

        val snapHelper = LinearSnapHelper()

        binding.walletRecyclerviewWallet.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            it.addItemDecoration(MarginItemDecoration(24))
            snapHelper.attachToRecyclerView(it)
        }

        globalViewModel.uiStateWallet.asLiveData().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.walletAddWallet.setOnClickListener {
            findNavController().navigate(R.id.action_walletFragment_to_addWalletActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WalletFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WalletFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
