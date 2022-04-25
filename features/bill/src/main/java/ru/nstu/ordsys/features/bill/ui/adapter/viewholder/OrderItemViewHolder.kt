package ru.nstu.ordsys.features.bill.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel

class OrderItemViewHolder(private val view: OrderItemView) : RecyclerView.ViewHolder(view) {

    fun setViewModel(viewModel: BaseViewModel) {
        view.viewModel = viewModel
    }
}