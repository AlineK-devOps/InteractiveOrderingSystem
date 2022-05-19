package ru.nstu.ordsys.features.waiter.orderlist.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nstu.ordsys.component.ui.animation.hideWithFade
import ru.nstu.ordsys.component.ui.animation.showWithFade
import ru.nstu.ordsys.component.ui.fragment.BaseView
import ru.nstu.ordsys.component.ui.recyclerview.setDivider
import ru.nstu.ordsys.features.waiter.orderlist.R
import ru.nstu.ordsys.features.waiter.orderlist.databinding.WaiterOrderListItemBinding
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderListForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.DeleteDishUseCase
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.PostDishStatusUseCase
import ru.nstu.ordsys.features.waiter.orderlist.presentation.WaiterItemOrderListViewModel
import ru.nstu.ordsys.features.waiter.orderlist.ui.adapter.WaiterItemOrderAdapter

@SuppressLint("ViewConstructor")
class WaiterOrderListView(
    parent: ViewGroup,
    private val postDishStatusUseCase: PostDishStatusUseCase,
    private val deleteDishUseCase: DeleteDishUseCase
) : BaseView(parent.context) {

    init {
        inflate(context, R.layout.waiter_order_list_item, this)
    }

    private var dishesAdapter: WaiterItemOrderAdapter? = null

    private val binding = WaiterOrderListItemBinding.bind(this)

    private val orderViewModel: WaiterItemOrderListViewModel
        get() = viewModel as WaiterItemOrderListViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        bindOrder(orderViewModel.itemOrder)
        setListeners()

        if (dishesAdapter == null)
            bindAdapter()

        dishesAdapter?.setItems(orderViewModel.itemOrder.order)
    }

    private fun bindAdapter() {
        dishesAdapter = WaiterItemOrderAdapter(postDishStatusUseCase, deleteDishUseCase)
        binding.dishesList.adapter = dishesAdapter
        binding.dishesList.layoutManager = LinearLayoutManager(context)
        binding.dishesList.setDivider(ru.nstu.ordsys.component.resources.R.drawable.thin_recycler_view_divider)
    }

    private fun bindOrder(item: OrderListForWaiter) {
        with(binding) {
            item.apply {
                tableNumber.text = resources.getString(
                    ru.nstu.ordsys.component.resources.R.string.table_number,
                    tableId
                )

                if (item.waiterCalled)
                    showWaiterCalling()
                else
                    hideWaiterCalling()
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            deleteOrderButton.setOnClickListener {
                orderViewModel.deleteOrder()
            }
            doneOrderButton.setOnClickListener {
                orderViewModel.doneOrder()
            }
            doneWaiterCallingButton.setOnClickListener {
                orderViewModel.doneWaiterCalling()
                hideWaiterCalling()
            }
        }
    }

    private fun showWaiterCalling(){
        binding.waiterCalling.showWithFade()
        binding.doneWaiterCallingButton.showWithFade()
    }

    private fun hideWaiterCalling(){
        binding.waiterCalling.hideWithFade()
        binding.doneWaiterCallingButton.hideWithFade()
    }
}