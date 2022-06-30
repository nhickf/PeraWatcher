package com.creativegrpcx.perawatcher.history

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.*
import androidx.viewbinding.ViewBinding
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.dashboard.DashboardItemRecyclerViewAdapter
import com.creativegrpcx.perawatcher.databinding.ItemSectionRecyclerLayoutBinding
import com.creativegrpcx.perawatcher.repository.entities.SectionedTransaction
import com.creativegrpcx.perawatcher.repository.entities.Transaction
import com.creativegrpcx.perawatcher.types.CategoryType

private lateinit var viewSection : ItemSectionRecyclerLayoutBinding

class DashboardItemSectionRecyclerViewAdapter : ListAdapter<SectionedTransaction,DashboardItemSectionRecyclerViewAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(DiffCallback()).build()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewSection = ItemSectionRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewSection)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val section = currentList[position]
        val itemAdapter = DashboardItemRecyclerViewAdapter()
        viewSection.itemSectionChip.let {
            it.text = section.sectionType.name
//            it.chipIcon = (ResourcesCompat.getDrawable(it.resources,translateCategories(section.sectionType),null))
        }
        viewSection.itemSectionRecyclerview.let {
            it.setHasFixedSize(true)
            it.adapter = itemAdapter
            it.layoutManager =
                LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL, false)
        }

        itemAdapter.submitList(section.sectionItems as ArrayList<Transaction>)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun submitList(list: MutableList<SectionedTransaction>?) {
        super.submitList(list?.sortedByDescending { sectionedTransaction -> sectionedTransaction.sectionItems.size })
    }


    class DiffCallback : DiffUtil.ItemCallback<SectionedTransaction>(){
        override fun areItemsTheSame(oldItem: SectionedTransaction, newItem: SectionedTransaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SectionedTransaction, newItem: SectionedTransaction): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder (private val viewMoney : ViewBinding) : RecyclerView.ViewHolder(viewMoney.root)

}
