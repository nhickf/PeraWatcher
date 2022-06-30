package com.creativegrpcx.perawatcher.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.creativegrpcx.perawatcher.BaseFragment
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.databinding.FragmentHistoryBinding
import com.creativegrpcx.perawatcher.databinding.FragmentStatisticsBinding
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAStyle

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatisticsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


private var _binding: FragmentStatisticsBinding? = null
private val binding get() = _binding!!

class StatisticsFragment : BaseFragment() {
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
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val elements = arrayListOf<AASeriesElement>()


        globalViewModel.loadSectionTransactions()

        globalViewModel.uiStateSectionedTransaction.asLiveData().observe(viewLifecycleOwner) { sections ->

             elements.addAll( sections.map { sec ->
                AASeriesElement()
                    .name(sec.sectionType.name)
                    .data(
                        arrayOf(sec.sectionItems.size)
                    )
            })

            val aaChartModel = AAChartModel()
                .chartType(AAChartType.Pie)
                .dataLabelsEnabled(true)
                .series(elements.toTypedArray())

            binding.fragmentStatisticsChartView.aa_drawChartWithChartModel(aaChartModel)

//            binding.fragmentStatisticsChartView.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(elements.toTypedArray())
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatisticsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatisticsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
