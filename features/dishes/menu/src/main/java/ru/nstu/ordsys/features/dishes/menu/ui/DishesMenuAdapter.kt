package ru.nstu.ordsys.features.dishes.menu.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.features.dishes.menu.presentation.DishViewModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class DishesMenuAdapter : RecyclerView.Adapter<DishViewHolder>() {

    private var dishesAdapterDataList = emptyList<DishesAdapterData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<Dish>) {
        dishesAdapterDataList = list.map { dish ->
            val viewModel = DishViewModel(dish)
            DishesAdapterData(dish, viewModel)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder =
        DishViewHolder(DishView(parent))

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.setViewModel(dishesAdapterDataList[position].viewModel)
    }

    override fun getItemCount(): Int = dishesAdapterDataList.size

    private data class DishesAdapterData(
        val dish: Dish,
        val viewModel: DishViewModel,
    )
}