package ru.nstu.ordsys.order.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.view.ViewGroup
import ru.nstu.ordsys.component.ui.fragment.BaseView
import ru.nstu.ordsys.component.resources.R
import ru.nstu.ordsys.order.databinding.OrderItemViewBinding
import ru.nstu.ordsys.order.presentation.OrderItemViewModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

@SuppressLint("ViewConstructor")
class OrderItemView(
    parent: ViewGroup
) : BaseView(parent.context) {

    init {
        inflate(context, ru.nstu.ordsys.order.R.layout.order_item_view, this)
    }

    private val binding = OrderItemViewBinding.bind(this)

    private val orderItemViewModel: OrderItemViewModel
        get() = viewModel as OrderItemViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        bindCardView(orderItemViewModel.dish, orderItemViewModel.countDish)
        setListeners()
        setObservers()
    }

    private fun bindCardView(dish: Dish, count: Int) {
        with(binding) {
            dish.apply {

                orderItemName.text = resources.getString(R.string.dish_name_format, name, weight)
                countText.text = count.toString()
                itemPrice.text = resources.getString(R.string.price_format, price * count )
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            addOneToCartButton.setOnClickListener {
                orderItemViewModel.addDishToOrder()
            }
            removeOneFromCartButton.setOnClickListener {
                orderItemViewModel.removeDishFromOrder()
            }
        }
    }

    private fun setObservers() {
        orderItemViewModel.count.observe(this, ::changeDishCount)
    }

    private fun changeDishCount(count: Int) {
        binding.countText.text = count.toString()
        binding.itemPrice.text = resources.getString(R.string.price_format, count * orderItemViewModel.dish.price)
    }
}