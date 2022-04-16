package ru.nstu.ordsys.features.dishes.menu.ui

import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel

class DishViewHolder(private val view: DishView) : RecyclerView.ViewHolder(view) {

    fun setViewModel(viewModel: BaseViewModel) {
        view.setViewModel(viewModel)
    }
}