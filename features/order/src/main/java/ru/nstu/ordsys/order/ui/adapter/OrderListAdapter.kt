package ru.nstu.ordsys.order.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.order.presentation.OrderItemViewModel
import ru.nstu.ordsys.order.ui.adapter.viewholder.OrderItemView
import ru.nstu.ordsys.order.ui.adapter.viewholder.OrderItemViewHolder
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class OrderListAdapter : RecyclerView.Adapter<OrderItemViewHolder>() {

    private var orderAdapterDataList = emptyList<OrderItemAdapterData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(order: HashMap<Dish, Int>) {
        orderAdapterDataList = order.map { orderItem ->
            val viewModel = OrderItemViewModel(orderItem.key, orderItem.value)
            OrderItemAdapterData(orderItem.key, orderItem.value, viewModel)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder =
        OrderItemViewHolder(OrderItemView(parent))

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        holder.setViewModel(orderAdapterDataList[position].viewModel)
    }

    override fun getItemCount(): Int = orderAdapterDataList.size

    private data class OrderItemAdapterData(
        val dish: Dish,
        val count: Int,
        val viewModel: OrderItemViewModel
    )
}