package ru.nstu.ordsys.features.bill.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.nstu.ordsys.features.bill.domain.entity.BillItem

class BillListAdapter :
    ListAdapter<BillItem, BillItemViewHolder>(BillListDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BillItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: BillItemViewHolder, position: Int) {
        holder.bind(getItem(position).dish, getItem(position).count)
    }
}

object BillListDiffCallBack : DiffUtil.ItemCallback<BillItem>() {

    override fun areItemsTheSame(oldItem: BillItem, newItem: BillItem) =
        oldItem.dish == newItem.dish

    override fun areContentsTheSame(oldItem: BillItem, newItem: BillItem) =
        oldItem == newItem
}