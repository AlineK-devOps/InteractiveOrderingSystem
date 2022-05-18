package ru.nstu.ordsys.features.cook.orderlist.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderListForCook
import ru.nstu.ordsys.features.cook.orderlist.presentation.CookItemOrderListViewModel
import ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder.CookOrderListView
import ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder.CookOrderListViewHolder

class CookOrderListAdapter : RecyclerView.Adapter<CookOrderListViewHolder>() {

    private var ordersAdapterDataList = emptyList<OrdersAdapterData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<OrderListForCook>) {
        ordersAdapterDataList = list.map { order ->
            val viewModel = CookItemOrderListViewModel(order)
            OrdersAdapterData(order, viewModel)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookOrderListViewHolder =
        CookOrderListViewHolder(CookOrderListView(parent))

    override fun onBindViewHolder(holder: CookOrderListViewHolder, position: Int) {
        holder.setViewModel(ordersAdapterDataList[position].viewModel)
    }

    override fun getItemCount(): Int = ordersAdapterDataList.size

    private data class OrdersAdapterData(
        val dish: OrderListForCook,
        val viewModel: CookItemOrderListViewModel
    )
}