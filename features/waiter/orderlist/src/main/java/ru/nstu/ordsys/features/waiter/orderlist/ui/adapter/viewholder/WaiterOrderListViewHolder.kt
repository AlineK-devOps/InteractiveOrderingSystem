package ru.nstu.ordsys.features.waiter.orderlist.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel

class WaiterOrderListViewHolder(private val view: WaiterOrderListView) :
    RecyclerView.ViewHolder(view) {

    fun setViewModel(viewModel: BaseViewModel) {
        view.viewModel = viewModel
    }
}