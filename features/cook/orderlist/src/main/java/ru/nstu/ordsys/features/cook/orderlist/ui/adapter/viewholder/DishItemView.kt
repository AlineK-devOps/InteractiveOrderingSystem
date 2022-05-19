package ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.os.SystemClock
import android.view.ViewGroup
import ru.nstu.ordsys.component.ui.animation.hideWithFade
import ru.nstu.ordsys.component.ui.animation.showWithFade
import ru.nstu.ordsys.component.ui.fragment.BaseView
import ru.nstu.ordsys.features.cook.orderlist.R
import ru.nstu.ordsys.features.cook.orderlist.databinding.DishItemBinding
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.DishForCook
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForCook
import ru.nstu.ordsys.features.cook.orderlist.presentation.DishItemViewModel
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus
import ru.nstu.ordsys.shared.user.entity.User
import java.sql.Timestamp
import java.util.*

@SuppressLint("ViewConstructor")
class DishItemView(
    parent: ViewGroup
) : BaseView(parent.context) {

    init {
        inflate(context, R.layout.dish_item, this)
    }

    private val binding = DishItemBinding.bind(this)

    private val dishViewModel: DishItemViewModel
        get() = viewModel as DishItemViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        setListeners()
        setObservers()
        bindItem(dishViewModel.dish)
    }

    private fun bindItem(item: OrderItemForCook) {
        with(binding) {
            item.apply {
                dishNameWeight.text = resources.getString(ru.nstu.ordsys.component.resources.R.string.dish_name_format, dish.name, dish.weight)
                dishTimeCooking.base = SystemClock.elapsedRealtime() - (Date().time + 7 * 60 *60 * 1000 - orderTime.time)
                dishTimeCooking.start()

                dishNameWeight.setOnClickListener {
                    if (!item.dish.recipe.isNullOrEmpty())
                        dishViewModel.navigateToTechnologyScreen(item.dish)
                }
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            startCookButton.setOnClickListener {
                dishViewModel.startCook()
            }
            endCookButton.setOnClickListener {
                dishViewModel.endCook()
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
            OrderItemStatus.IN_QUEUE_FOR_COOKING -> renderInQueueForCookingStatus()
            OrderItemStatus.IN_COOKING_PROCESS   -> renderInCookingProcessStatus()
            OrderItemStatus.IN_QUEUE_FOR_SERVING -> renderInQueueForServingStatus()
            else                                 -> throw Exception("Wrong dish status")
        }
    }

    private fun renderInQueueForCookingStatus() {
        with(binding) {
            cookingStatus.hideWithFade()
            endCookButton.hideWithFade()
            startCookButton.showWithFade()
        }
    }

    private fun renderInCookingProcessStatus() {
        with(binding) {
            startCookButton.hideWithFade()

            if (dishViewModel.dish.cook == User.getName())
                endCookButton.showWithFade()
            else{
                cookingStatus.text = resources.getString(ru.nstu.ordsys.component.resources.R.string.cooking_status_format, dishViewModel.dish.cook)
                cookingStatus.showWithFade()
            }
        }
    }

    private fun renderInQueueForServingStatus() {
        with(binding) {
            endCookButton.hideWithFade()
            startCookButton.hideWithFade()
            cookingStatus.text = resources.getString(ru.nstu.ordsys.component.resources.R.string.ready_status)
            cookingStatus.showWithFade()
        }
    }
}