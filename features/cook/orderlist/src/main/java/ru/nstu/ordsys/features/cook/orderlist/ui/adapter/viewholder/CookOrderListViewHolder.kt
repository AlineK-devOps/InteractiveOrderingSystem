package ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel

class CookOrderListViewHolder(private val view: CookOrderListView) : RecyclerView.ViewHolder(view) {

    fun setViewModel(viewModel: BaseViewModel) {
        view.viewModel = viewModel
    }
}