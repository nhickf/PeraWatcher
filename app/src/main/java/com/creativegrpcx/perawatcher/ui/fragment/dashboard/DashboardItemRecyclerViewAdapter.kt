package com.creativegrpcx.perawatcher.ui.fragment.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.databinding.RecyclerviewTransactionItemLayoutBinding
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private lateinit var viewMoney : RecyclerviewTransactionItemLayoutBinding

class DashboardItemRecyclerViewAdapter : ListAdapter<Transaction,DashboardItemRecyclerViewAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(DiffCallback()).build()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewMoney = RecyclerviewTransactionItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewMoney)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = currentList[position]
        viewMoney.transactionItemAmount.let {
            it.text = "$${String.format("%.2f",transaction.amount)}"
//            it.setTextColor(
//                ResourcesCompat.getColor(holder.itemView.resources,if (transaction.amount < 0)R.color.light_red_money  else R.color.light_green_money,null)
//            )
        }

        val formatDate = LocalDate.parse(transaction.date).format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
        val formatTime = LocalTime.parse(transaction.time).format(DateTimeFormatter.ofPattern("hh:mm a"))

        viewMoney.transactionItemDatetime.text = "$formatDate - $formatTime"
        viewMoney.transactionItemTitle.text = transaction.title
        viewMoney.transactionItemCategory.setImageResource(translateCategories(transaction.category))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun submitList(list: MutableList<Transaction>?) {
        super.submitList(list?.sortedByDescending { transaction -> "${transaction.date} ${transaction.time}" })
    }

    private fun translateCategories (category: CategoryType) : Int{
        return when(category){
            CategoryType.Appliance -> R.drawable.baseline_coffee_maker_24
            CategoryType.BeautyAndPersonalCare -> R.drawable.baseline_face_24
            CategoryType.Bills -> R.drawable.baseline_receipt_24
            CategoryType.ComputerAndITAccessories -> R.drawable.outline_computer_24
            CategoryType.Electronics -> R.drawable.baseline_photo_camera_24
            CategoryType.FoodAndGroceries -> R.drawable.baseline_local_grocery_store_24
            CategoryType.Furniture -> R.drawable.baseline_chair_24
            CategoryType.Fashion -> R.drawable.baseline_shopping_bag_24
            CategoryType.Health -> R.drawable.baseline_health_and_safety_24
            CategoryType.HobbiesAndLeisure -> R.drawable.baseline_sports_esports_24
            CategoryType.PetSupplies -> R.drawable.outline_pets_24
            CategoryType.Subscription -> R.drawable.baseline_subscriptions_24
            else -> R.drawable.baseline_question_mark_24
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Transaction>(){
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.transactionId == newItem.transactionId
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder (private val viewMoney : ViewBinding) : RecyclerView.ViewHolder(viewMoney.root)

}
