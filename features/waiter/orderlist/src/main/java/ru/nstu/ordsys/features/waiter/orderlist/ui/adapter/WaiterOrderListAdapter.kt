package ru.nstu.ordsys.features.waiter.orderlist.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderListForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.*
import ru.nstu.ordsys.features.waiter.orderlist.presentation.WaiterItemOrderListViewModel
import ru.nstu.ordsys.features.waiter.orderlist.ui.adapter.viewholder.WaiterOrderListView
import ru.nstu.ordsys.features.waiter.orderlist.ui.adapter.viewholder.WaiterOrderListViewHolder

class WaiterOrderListAdapter(
    private val postDishStatusUseCase: PostDishStatusUseCase,
    private val doneWaiterCallingUseCase: DoneWaiterCallingUseCase,
    private val deleteDishUseCase: DeleteDishUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase,
    private val doneOrderUseCase: DoneOrderUseCase
) : RecyclerView.Adapter<WaiterOrderListViewHolder>() {

    private var ordersAdapterDataList = emptyList<OrdersAdapterData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<OrderListForWaiter>) {
        ordersAdapterDataList = list.map { order ->
            val viewModel = WaiterItemOrderListViewModel(order, doneWaiterCallingUseCase, deleteOrderUseCase, doneOrderUseCase)
            OrdersAdapterData(order, viewModel)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaiterOrderListViewHolder =
        WaiterOrderListViewHolder(WaiterOrderListView(parent, postDishStatusUseCase, deleteDishUseCase))

    override fun onBindViewHolder(holder: WaiterOrderListViewHolder, position: Int) {
        holder.setViewModel(ordersAdapterDataList[position].viewModel)
    }

    override fun getItemCount(): Int = ordersAdapterDataList.size

    private data class OrdersAdapterData(
        val dish: OrderListForWaiter,
        val viewModel: WaiterItemOrderListViewModel
    )
}