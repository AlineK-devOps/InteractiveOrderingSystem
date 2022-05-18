package ru.nstu.ordsys.features.cook.orderlist.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForCook
import ru.nstu.ordsys.features.cook.orderlist.presentation.DishItemViewModel
import ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder.DishItemView
import ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder.DishItemViewHolder

class CookItemOrderAdapter : RecyclerView.Adapter<DishItemViewHolder>() {

    private var dishesAdapterDataList = emptyList<DishesAdapterData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<OrderItemForCook>) {
        dishesAdapterDataList = list.map { dish ->
            val viewModel = DishItemViewModel(dish)
            DishesAdapterData(dish, viewModel)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishItemViewHolder =
        DishItemViewHolder(DishItemView(parent))

    override fun onBindViewHolder(holder: DishItemViewHolder, position: Int) {
        holder.setViewModel(dishesAdapterDataList[position].viewModel)
    }

    override fun getItemCount(): Int = dishesAdapterDataList.size

    private data class DishesAdapterData(
        val dish: OrderItemForCook,
        val viewModel: DishItemViewModel
    )
}