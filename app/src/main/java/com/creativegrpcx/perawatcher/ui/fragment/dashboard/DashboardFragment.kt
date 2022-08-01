package com.creativegrpcx.perawatcher.ui.fragment.dashboard

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
import com.creativegrpcx.perawatcher.*
import com.creativegrpcx.perawatcher.ui.utils.MarginItemDecoration
import com.creativegrpcx.perawatcher.data.repository.entities.DataAdapter
import com.creativegrpcx.perawatcher.databinding.FragmentDashboardBinding
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.ui.fragment.main.BaseFragment
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModel
import com.creativegrpcx.perawatcher.ui.fragment.BottomSheetAddMoney

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private var _binding : FragmentDashboardBinding? = null
private val binding get() = _binding!!
private val adapter by lazy {
    DataAdapter(
        DashboardItemRecyclerViewAdapter(),
        DashboardWalletRecyclerViewAdapter()
    )
 }

class DashboardFragment :
    BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val globalViewModel: GlobalViewModel by viewModels {
        globalViewModelFactory
    }

    private val bottomSheetAddMoney : BottomSheetAddMoney by lazy {
        (activity?.application as MainApplication).bottomSheet
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
       _binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadItems()

        binding.let {  dashboard ->

            val snapHelper = LinearSnapHelper()

            dashboard.fragmentDashboardRecyclerView.let {
                it.adapter = adapter.item
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

            dashboard.dashboardRecyclerViewMoney.let {
                it.adapter = adapter.wallet.apply {
                    this?.addOnclickCallback {
                        bottomSheetAddMoney.show(
                            this@DashboardFragment.childFragmentManager,
                            "Add money"
                        )
                    }
                }

                it.addItemDecoration(MarginItemDecoration(24))


                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                snapHelper.attachToRecyclerView(it)
            }



            dashboard.dashboardAddTransaction.setOnClickListener {
                findNavController().navigate(R.id.action_dashboardFragment_to_transactionActivity)
            }

        }

        globalViewModel.uiStateTransaction.asLiveData().observe(viewLifecycleOwner) {
            adapter.item.submitList(it as ArrayList<Transaction>)
        }

        globalViewModel.uiStateWallet.asLiveData().observe(viewLifecycleOwner) {
            adapter.wallet?.submitList(it)
        }

    }

    private fun loadItems(){
        globalViewModel.loadTransactions()
        globalViewModel.loadWallet()
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
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
