package ru.nstu.ordsys.order.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.view.ViewGroup
import ru.nstu.ordsys.features.dishes.menu.ui.adapter.viewholder.BaseView
import ru.nstu.ordsys.order.R
import ru.nstu.ordsys.order.databinding.OrderItemViewBinding
import ru.nstu.ordsys.order.presentation.OrderItemViewModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

@SuppressLint("ViewConstructor")
class OrderItemView(
    parent: ViewGroup
) : BaseView(parent.context) {

    init {
        inflate(context, R.layout.order_item_view, this)
    }

    private val binding = OrderItemViewBinding.bind(this)

    private val orderItemViewModel: OrderItemViewModel
        get() = viewModel as OrderItemViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        bindCardView()
        setListeners()
        setObservers()
    }

    private fun bindCardView(dish: Dish, count: Int) {
        with(binding) {
            dish.apply {

                orderItemName.text = resources.getString(ru.nstu.ordsys.features.dishes.menu.R.string.dish_name_format, name, weight)
                countText.text = count.toString()
                itemPrice.text = resources.getString(ru.nstu.ordsys.features.dishes.menu.R.string.price_format, price * count )
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
}