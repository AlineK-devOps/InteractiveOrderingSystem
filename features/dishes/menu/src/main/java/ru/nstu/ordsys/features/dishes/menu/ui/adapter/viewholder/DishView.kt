package ru.nstu.ordsys.features.dishes.menu.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import ru.nstu.ordsys.features.dishes.menu.R
import ru.nstu.ordsys.features.dishes.menu.databinding.DishesListItemBinding
import ru.nstu.ordsys.features.dishes.menu.presentation.DishViewModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

@SuppressLint("ViewConstructor")
class DishView(
    parent: ViewGroup
) : BaseView(parent.context) {

    init {
        inflate(context, R.layout.dishes_list_item, this)
    }

    private val binding = DishesListItemBinding.bind(this)

    private val dishViewModel: DishViewModel
        get() = viewModel as DishViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        bindCardView(dishViewModel.dish)
        //setListeners()
    }

    private fun bindCardView(dish: Dish) {
        with(binding) {
            dish.apply {

                dishName.text = resources.getString(R.string.dish_name_format, name, weight)
                dishComposition.text = composition
                dishPrice.text = resources.getString(R.string.dish_price_format, price)

                Picasso
                    .get()
                    .load(imageUrl)
                    .placeholder(ru.nstu.ordsys.component.resources.R.drawable.ic_loading)
                    .error(ru.nstu.ordsys.component.resources.R.drawable.ic_image_not_supported)
                    .into(dishImage)
            }
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