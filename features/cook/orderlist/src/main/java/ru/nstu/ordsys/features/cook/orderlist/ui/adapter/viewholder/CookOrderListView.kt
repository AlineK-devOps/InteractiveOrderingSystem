package ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nstu.ordsys.component.ui.fragment.BaseView
import ru.nstu.ordsys.component.ui.recyclerview.setDivider
import ru.nstu.ordsys.features.cook.orderlist.R
import ru.nstu.ordsys.features.cook.orderlist.databinding.CookOrderListItemBinding
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.DishForCook
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderListForCook
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.PostDishStatusUseCase
import ru.nstu.ordsys.features.cook.orderlist.presentation.CookItemOrderListViewModel
import ru.nstu.ordsys.features.cook.orderlist.presentation.CookOrderItemRouter
import ru.nstu.ordsys.features.cook.orderlist.ui.adapter.CookItemOrderAdapter
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

@SuppressLint("ViewConstructor")
class CookOrderListView(
    parent: ViewGroup,
    val useCase: PostDishStatusUseCase,
    val router: CookOrderItemRouter
) : BaseView(parent.context) {

    init {
        inflate(context, R.layout.cook_order_list_item, this)
    }

    private var dishesAdapter: CookItemOrderAdapter? = null

    private val binding = CookOrderListItemBinding.bind(this)

    private val dishViewModel: CookItemOrderListViewModel
        get() = viewModel as CookItemOrderListViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        bindOrder(dishViewModel.itemOrder)

        if (dishesAdapter == null)
            bindAdapter()

        dishesAdapter?.setItems(dishViewModel.itemOrder.order)
    }

    private fun bindAdapter() {
        dishesAdapter = CookItemOrderAdapter(useCase, router)
        binding.dishesList.adapter = dishesAdapter
        binding.dishesList.layoutManager = LinearLayoutManager(context)
        binding.dishesList.setDivider(ru.nstu.ordsys.component.resources.R.drawable.thin_recycler_view_divider)
    }

    private fun bindOrder(item: OrderListForCook) {
        with(binding) {
            item.apply {
                tableNumber.text = resources.getString(
                    ru.nstu.ordsys.component.resources.R.string.table_number,
                    tableId
                )
            }
        }
    }
}