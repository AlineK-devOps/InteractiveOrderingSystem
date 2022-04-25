package ru.nstu.ordsys.features.dishes.menu.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.dishes.menu.ui.adapter.viewholder.DishView

class DishViewHolder(private val view: DishView) : RecyclerView.ViewHolder(view) {

    fun setViewModel(viewModel: BaseViewModel) {
        view.viewModel = viewModel
    }
}