package ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.DishForCook

class DishItemViewHolder(
    private val view: DishItemView
) : RecyclerView.ViewHolder(view) {

    fun setViewModel(viewModel: BaseViewModel) {
        view.viewModel = viewModel
    }
}