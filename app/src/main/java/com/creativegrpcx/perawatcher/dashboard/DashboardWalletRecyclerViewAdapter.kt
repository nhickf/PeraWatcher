package com.creativegrpcx.perawatcher.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.creativegrpcx.perawatcher.databinding.RecyclerviewWalletItemLayoutBinding
import com.creativegrpcx.perawatcher.repository.entities.Wallet

private lateinit var viewWallet : RecyclerviewWalletItemLayoutBinding

class DashboardWalletRecyclerViewAdapter : ListAdapter<Wallet,DashboardWalletRecyclerViewAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(DiffCallback()).build()){

    private var onAddMoneyClick: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewWallet = RecyclerviewWalletItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewWallet)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallet = currentList[position]
        viewWallet.walletItemAmount.let {
            it.text = "$${String.format("%.2f",wallet.walletAmount)}"
        }
        viewWallet.walletItemName.text = wallet.walletName

        viewWallet.walletItemAddAmount.setOnClickListener(onAddMoneyClick)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    fun addOnclickCallback(callback : View.OnClickListener){
        onAddMoneyClick = callback
    }


    class  DiffCallback : DiffUtil.ItemCallback<Wallet>(){
       override fun areItemsTheSame(oldItem: Wallet, newItem: Wallet): Boolean {
          return oldItem.walletId == newItem.walletId
       }

       override fun areContentsTheSame(oldItem: Wallet, newItem: Wallet): Boolean {
           return oldItem == newItem
       }

   }

   inner class ViewHolder (private val viewMoney : ViewBinding) : RecyclerView.ViewHolder(viewMoney.root)

}
