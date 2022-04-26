package ru.nstu.ordsys.features.bill.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.nstu.ordsys.features.bill.R
import ru.nstu.ordsys.features.bill.databinding.BillItemViewBinding
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class BillItemViewHolder(
    private val binding: BillItemViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun from(parent: ViewGroup): BillItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = BillItemViewBinding.inflate(inflater, parent, false)
            return BillItemViewHolder(binding)
        }
    }

    fun bind(dish: Dish, count: Int) {
        with(binding) {
            billItemName.text = binding.root.context.getString(ru.nstu.ordsys.component.resources.R.string.bill_dish_name_format, dish.name, count)
            billItemPrice.text = binding.root.context.getString(ru.nstu.ordsys.component.resources.R.string.price_format, count * dish.price)
        }
    }
}