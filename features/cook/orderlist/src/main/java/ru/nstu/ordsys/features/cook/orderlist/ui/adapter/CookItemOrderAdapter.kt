package ru.nstu.ordsys.features.cook.orderlist.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.koin.java.KoinJavaComponent.inject
import ru.nstu.ordsys.features.cook.orderlist.data.api.CookOrderListApi
import ru.nstu.ordsys.features.cook.orderlist.data.datasource.CookOrderListDataSourceImpl
import ru.nstu.ordsys.features.cook.orderlist.data.repository.CookOrderListRepositoryImpl
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForCook
import ru.nstu.ordsys.features.cook.orderlist.domain.repository.CookOrderListRepository
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.PostDishStatusUseCase
import ru.nstu.ordsys.features.cook.orderlist.presentation.DishItemViewModel
import ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder.DishItemView
import ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder.DishItemViewHolder

class CookItemOrderAdapter(
    val useCase: PostDishStatusUseCase
) : RecyclerView.Adapter<DishItemViewHolder>() {

    private var dishesAdapterDataList = emptyList<DishesAdapterData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<OrderItemForCook>) {
        dishesAdapterDataList = list.map { dish ->
            val viewModel = DishItemViewModel(dish, useCase)
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