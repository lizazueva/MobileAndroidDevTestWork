package com.ntpro.mobileandroiddevtestwork

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ntpro.mobileandroiddevtestwork.databinding.ItemDealBinding

class AdapterDeal : RecyclerView.Adapter<AdapterDeal.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDeal.ViewHolder {
        val binding = ItemDealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterDeal.ViewHolder, position: Int) {
        val deal = differ.currentList[position]
        with(holder.binding) {
            rowDateOfChange.text = deal.timeStamp.toString()
            rowName.text = deal.instrumentName
            rowPrice.text = setFormatDouble(deal.price)
            rowVolume.text = setFormatDoubleRound(deal.amount)
            rowParty.text = deal.side.toString()

            val color = if (deal.side == Server.Deal.Side.BUY) Color.GREEN else Color.RED
            rowPrice.setTextColor(color)
        }
    }

    private fun setFormatDouble(value: Double): String {
        return String.format("%.2f", value)
    }

    private fun setFormatDoubleRound(value: Double): String {
        return String.format("%.0f", value)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(var binding: ItemDealBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Server.Deal>() {
        override fun areItemsTheSame(oldItem: Server.Deal, newItem: Server.Deal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Server.Deal, newItem: Server.Deal): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}