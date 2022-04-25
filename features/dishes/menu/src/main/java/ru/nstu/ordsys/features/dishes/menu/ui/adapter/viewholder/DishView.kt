package ru.nstu.ordsys.features.dishes.menu.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import ru.nstu.ordsys.component.resources.R
import ru.nstu.ordsys.component.ui.animation.hideWithFade
import ru.nstu.ordsys.component.ui.animation.showWithFade
import ru.nstu.ordsys.component.ui.fragment.BaseView
import ru.nstu.ordsys.features.dishes.menu.databinding.DishesListItemBinding
import ru.nstu.ordsys.features.dishes.menu.presentation.DishViewModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

@SuppressLint("ViewConstructor")
class DishView(
    parent: ViewGroup
) : BaseView(parent.context) {

    init {
        inflate(context, ru.nstu.ordsys.features.dishes.menu.R.layout.dishes_list_item, this)
    }

    private val binding = DishesListItemBinding.bind(this)

    private val dishViewModel: DishViewModel
        get() = viewModel as DishViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        bindCardView(dishViewModel.dish)
        setListeners()
        setObservers()


    }

    private fun bindCardView(dish: Dish) {
        with(binding) {
            dish.apply {

                dishName.text = resources.getString(R.string.dish_name_format, name, weight)
                dishComposition.text = composition
                dishPrice.text = resources.getString(R.string.price_format, price)

                Picasso
                    .get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_image_not_supported)
                    .into(dishImage)
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            addToCartButton.setOnClickListener {
                dishViewModel.addDishToOrder()
            }
            addOneToCartButton.setOnClickListener {
                dishViewModel.addDishToOrder()
            }
            removeOneFromCartButton.setOnClickListener {
                dishViewModel.removeDishFromOrder()
            }
        }
    }

    private fun setObservers() {
        dishViewModel.count.observe(this, ::changeDishCount)
    }

    private fun changeDishCount(count: Int) {
        if (count > 0) {
            showCountPicker()
            binding.count.text = count.toString()
        } else
            hideCountPicker()
    }

    private fun showCountPicker() {
        binding.addToCartButton.hideWithFade()
        binding.countPicker.showWithFade()
    }

    private fun hideCountPicker() {
        binding.addToCartButton.showWithFade()
        binding.countPicker.hideWithFade()
    }
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