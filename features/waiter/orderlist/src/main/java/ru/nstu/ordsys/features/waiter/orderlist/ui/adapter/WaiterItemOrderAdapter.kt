package ru.nstu.ordsys.features.waiter.orderlist.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderItemForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.DeleteDishUseCase
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.PostDishStatusUseCase
import ru.nstu.ordsys.features.waiter.orderlist.presentation.DishItemViewModel
import ru.nstu.ordsys.features.waiter.orderlist.ui.adapter.viewholder.DishItemView
import ru.nstu.ordsys.features.waiter.orderlist.ui.adapter.viewholder.DishItemViewHolder

class WaiterItemOrderAdapter(
    private val postDishStatusUseCase: PostDishStatusUseCase,
    private val deleteDishUseCase: DeleteDishUseCase
) : RecyclerView.Adapter<DishItemViewHolder>() {

    private var dishesAdapterDataList = emptyList<DishesAdapterData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<OrderItemForWaiter>) {
        dishesAdapterDataList = list.map { dish ->
            val viewModel = DishItemViewModel(dish, postDishStatusUseCase, deleteDishUseCase)
            DishesAdapterData(dish, viewModel)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishItemViewHolder {
        return DishItemViewHolder(DishItemView(parent))
    }

    override fun onBindViewHolder(holder: DishItemViewHolder, position: Int) {
        holder.setViewModel(dishesAdapterDataList[position].viewModel)
    }

    override fun getItemCount(): Int = dishesAdapterDataList.size

    private data class DishesAdapterData(
        val dish: OrderItemForWaiter,
        val viewModel: DishItemViewModel
    )
}