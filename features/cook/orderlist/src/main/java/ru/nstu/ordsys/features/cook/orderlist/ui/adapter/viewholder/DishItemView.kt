package ru.nstu.ordsys.features.cook.orderlist.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.view.ViewGroup
import ru.nstu.ordsys.component.ui.fragment.BaseView
import ru.nstu.ordsys.features.cook.orderlist.R
import ru.nstu.ordsys.features.cook.orderlist.databinding.DishItemBinding
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForCook
import ru.nstu.ordsys.features.cook.orderlist.presentation.DishItemViewModel
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus

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

        bindItem(dishViewModel.dish)
        setListeners()
        setObservers()
    }

    private fun bindItem(item: OrderItemForCook) {
        with(binding) {
            item.apply {
                dishNameWeight.text = resources.getString(ru.nstu.ordsys.component.resources.R.string.dish_name_format, dish.name, dish.weight)
                dishTimeCooking.text = "0:0"
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            startCookButton.setOnClickListener {
                //dishViewModel.startCook()
            }
            endCookButton.setOnClickListener {
                //dishViewModel.endCook()
            }
        }
    }

    private fun setObservers() {
        //dishViewModel.status.observe(this, ::handleStatus)
    }

    private fun handleStatus(status: OrderItemStatus) {
//        when (status) {
//            OrderItemStatus.IN_QUEUE_FOR_COOKING      -> renderInQueueForCookingStatus()
//            OrderItemStatus.IN_COOKING_PROCESS     -> renderDeclinedStatus()
//            OrderItemStatus.IN_QUEUE_FOR_SERVING -> renderUnderReviewStatus()
//            OrderItemStatus.SERVED -> renderUnderReviewStatus()b
//        }
    }

    private fun renderSuccessStatus() {
        with(binding) {
        }
    }
}