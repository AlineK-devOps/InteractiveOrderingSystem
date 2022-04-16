package ru.nstu.ordsys.features.dishes.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ru.nstu.ordsys.component.ui.fragment.BaseFragment
import ru.nstu.ordsys.features.dishes.menu.R
import ru.nstu.ordsys.features.dishes.menu.databinding.DishesMenuFragmentBinding
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesMenuState
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesMenuViewModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish
import org.koin.androidx.viewmodel.ext.android.viewModel


class DishesMenuFragment : BaseFragment<DishesMenuFragmentBinding>(R.layout.dishes_menu_fragment) {

    companion object {

        fun newInstance() = DishesMenuFragment()
    }

    private val viewModel: DishesMenuViewModel by viewModel()

    private lateinit var menuAdapter: DishesMenuAdapter
    private lateinit var listAdapter: ArrayAdapter<String>

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DishesMenuFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindAdapter()
        setListeners()
        //setObservers()
    }

    private fun bindAdapter() {
        listAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dishes_category_tab,
            resources.getStringArray(R.array.category_name)
        )
        binding.menuTabs.adapter = listAdapter

        binding.menuTabs.setOnItemClickListener { parent, itemClicked, position, id ->
            val category = resources.getStringArray(R.array.category_name)[position]

            when (category) {
                "Роллы"            -> viewModel.getRollsMenu()
                "Горячие роллы"    -> viewModel.getHotRollsMenu()
                "Суши"             -> viewModel.getSushiMenu()
                "Салаты и закуски" -> viewModel.getSnacksMenu()
                "Вок"              -> viewModel.getWokMenu()
                "Супы"             -> viewModel.getSoupsMenu()
                "Напитки"          -> viewModel.getDrinksMenu()
                "Дополнительно"    -> viewModel.getAdditionallyMenu()
            }
        }

        binding.menuTabs.setSelection(0)

        menuAdapter = DishesMenuAdapter()
        binding.dishesList.adapter = menuAdapter
    }

    private fun setListeners() {
        binding.waiterCallingButton.setOnClickListener {

        }
        binding.orderButton.setOnClickListener {

        }
        binding.billButton.setOnClickListener {

        }
    }

//    private fun setObservers() {
//        viewModel.state.observe(viewLifecycleOwner, ::handleState)
//    }

    private fun handleState(state: DishesMenuState) {
        when (state) {
            //is DishesMenuState.Initial,
            //is DishesMenuState.Loading      -> renderLoadingState()
            //is DishesMenuState.Error        -> renderErrorState()
            is DishesMenuState.Content      -> renderContentState(state.dishesMenu)
            else -> Unit
        }
    }

    //
//    private fun renderLoadingState() {
//        errorNoticeBinding?.root?.hideWithFade()
//        binding.progressBar.showWithFade()
//    }
//
//    private fun renderErrorState() {
//        hideProgressBar()
//        inflateOrShowErrorNotice()
//    }
//
    private fun renderContentState(dishesMenu: List<Dish>) {
        //hideProgressBar()
        menuAdapter.setItems(dishesMenu)
    }
//
//    private fun hideProgressBar() {
//        binding.progressBar.hideWithFade()
//    }
}