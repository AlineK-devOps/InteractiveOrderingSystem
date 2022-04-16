package ru.nstu.ordsys.features.dishes.menu.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import ru.nstu.ordsys.features.dishes.menu.R
import ru.nstu.ordsys.features.dishes.menu.databinding.DishMenuItemBinding
import ru.nstu.ordsys.features.dishes.menu.presentation.DishViewModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

@SuppressLint("ViewConstructor")
class DishView(
    parent: ViewGroup
) : BaseView(parent.context) {

    init {
        inflate(context, R.layout.dish_menu_item, this)
    }

    private val binding = DishMenuItemBinding.bind(this)

    private val dishViewModel: DishViewModel
        get() = viewModel as DishViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        bindCardView(dishViewModel.dish)
        //setListeners()
    }

    private fun bindCardView(dish: Dish) {
        with(binding) {
            dishName.text = dish.name
            price.text = dish.price.toString()
        }
    }
//
//    private fun setListeners() {
//        with(binding) {
//            approveButton.setOnClickListener {
//                deliveryViewModel.approveRequest()
//            }
//            declineButton.setOnClickListener {
//                deliveryViewModel.declineRequest()
//            }
//        }
//    }
//
//    private fun handleStatus(status: DeliveryRequestsStatus) {
//        when (status) {
//            DeliveryRequestsStatus.SUCCESS      -> renderSuccessStatus()
//            DeliveryRequestsStatus.DECLINED     -> renderDeclinedStatus()
//            DeliveryRequestsStatus.UNDER_REVIEW -> renderUnderReviewStatus()
//            DeliveryRequestsStatus.UNKNOWN      -> throw IllegalArgumentException("Invalid status of task")
//        }
//    }
//
//    private fun renderSuccessStatus() {
//        with(binding) {
//            approveButton.visibility = View.GONE
//            declineButton.visibility = View.GONE
//            statusText.visibility = View.VISIBLE
//            statusTextview.visibility = View.VISIBLE
//            statusTextview.text = resources.getString(R.string.tasks_request_approved)
//        }
//    }
//
//    private fun renderDeclinedStatus() {
//        with(binding) {
//            approveButton.visibility = View.GONE
//            declineButton.visibility = View.GONE
//            statusText.visibility = View.VISIBLE
//            statusTextview.visibility = View.VISIBLE
//            statusTextview.text = resources.getString(R.string.tasks_request_declined)
//        }
//    }
//
//    private fun renderUnderReviewStatus() {
//        with(binding) {
//            approveButton.visibility = View.VISIBLE
//            declineButton.visibility = View.VISIBLE
//            statusText.visibility = View.GONE
//            statusTextview.visibility = View.GONE
//        }
//    }
}