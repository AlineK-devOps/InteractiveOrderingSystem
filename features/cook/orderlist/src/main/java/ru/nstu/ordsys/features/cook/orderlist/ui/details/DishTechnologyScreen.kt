package ru.nstu.ordsys.features.cook.orderlist.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.nstu.ordsys.component.resources.R
import ru.nstu.ordsys.component.ui.fragment.BaseFragment
import ru.nstu.ordsys.features.cook.orderlist.databinding.DishTechnologyFragmentBinding
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.DishForCook
import ru.nstu.ordsys.features.cook.orderlist.presentation.details.DishTechnologyViewModel

class DishTechnologyScreen(
    private val dish: DishForCook
) :
    BaseFragment<DishTechnologyFragmentBinding>(ru.nstu.ordsys.features.cook.orderlist.R.layout.dish_technology_fragment) {

    companion object {

        fun newInstance(dish: DishForCook) = DishTechnologyScreen(dish)
    }

    private val viewModel: DishTechnologyViewModel by viewModel()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DishTechnologyFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindDish()
        setListener()
    }

    private fun bindDish() {
        with(binding) {

            dishName.text = resources.getString(R.string.dish_technology_format, dish.name)
            dishRecipe.text = dish.recipe

            Picasso
                .get()
                .load(dish.imageUrl)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_image_not_supported)
                .into(dishImage)
        }
    }

    private fun setListener() {
        binding.backButton.setOnClickListener {
            viewModel.exit()
        }
    }
}