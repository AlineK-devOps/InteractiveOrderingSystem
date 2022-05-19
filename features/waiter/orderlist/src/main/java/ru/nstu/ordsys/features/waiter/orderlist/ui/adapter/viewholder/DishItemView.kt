package ru.nstu.ordsys.features.waiter.orderlist.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.os.SystemClock
import android.view.ViewGroup
import ru.nstu.ordsys.component.ui.animation.hideWithFade
import ru.nstu.ordsys.component.ui.animation.showWithFade
import ru.nstu.ordsys.component.ui.fragment.BaseView
import ru.nstu.ordsys.features.waiter.orderlist.R
import ru.nstu.ordsys.features.waiter.orderlist.databinding.WaiterDishItemBinding
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderItemForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.presentation.DishItemViewModel
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus
import java.util.*

@SuppressLint("ViewConstructor")
class DishItemView(
    parent: ViewGroup
) : BaseView(parent.context) {

    init {
        inflate(context, R.layout.waiter_dish_item, this)
    }

    private val binding = WaiterDishItemBinding.bind(this)

    private val dishViewModel: DishItemViewModel
        get() = viewModel as DishItemViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        setListeners()
        setObservers()
        bindItem(dishViewModel.dish)
    }

    private fun bindItem(item: OrderItemForWaiter) {
        with(binding) {
            item.apply {
                dishNameWeight.text = resources.getString(
                    ru.nstu.ordsys.component.resources.R.string.dish_name_format,
                    dish.name,
                    dish.weight
                )
                dishTimeCooking.base =
                    SystemClock.elapsedRealtime() - (Date().time + 7 * 60 * 60 * 1000 - orderTime.time)
                dishTimeCooking.start()
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            servingButton.setOnClickListener {
                dishViewModel.servingDish()
            }
            deleteDishWaiterButton.setOnClickListener {
                dishViewModel.deleteDish()
            }
            dishTimeCooking.setOnChronometerTickListener {
                val elapsedMillis: Long = (SystemClock.elapsedRealtime() - dishTimeCooking.base)
                if (elapsedMillis > dishViewModel.dish.dish.cookingTime * 60 * 1000)
                    dishTimeCooking.setBackgroundColor(resources.getColor(ru.nstu.ordsys.component.resources.R.color.red))
            }
        }
    }

    private fun setObservers() {
        dishViewModel.status.observe(this, ::handleStatus)
    }

    private fun handleStatus(status: OrderItemStatus) {
        when (status) {
            OrderItemStatus.SERVED,
            OrderItemStatus.IN_QUEUE_FOR_COOKING,
            OrderItemStatus.IN_COOKING_PROCESS -> renderCookingStatus()
            OrderItemStatus.IN_QUEUE_FOR_SERVING -> renderInQueueForServingStatus()
        }
    }

    private fun renderInQueueForServingStatus() {
        with(binding) {
            cookingStatus.hideWithFade()
            servingButton.showWithFade()
        }
    }

    private fun renderCookingStatus() {
        with(binding) {
            cookingStatus.text = dishViewModel.status.value?.localized
            cookingStatus.showWithFade()
            servingButton.hideWithFade()
        }
    }
}