package ru.nstu.ordsys.features.dishes.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ru.nstu.ordsys.component.ui.fragment.BaseFragment
import ru.nstu.ordsys.features.dishes.menu.R
import ru.nstu.ordsys.features.dishes.menu.databinding.DishesMenuFragmentBinding
import ru.nstu.ordsys.features.dishes.menu.presentation.state.DishesMenuState
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesMenuViewModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.nstu.ordsys.component.ui.animation.hideWithFade
import ru.nstu.ordsys.component.ui.animation.showWithFade
import ru.nstu.ordsys.component.ui.dialog.showCustomDialog
import ru.nstu.ordsys.features.dishes.menu.ui.adapter.DishesMenuAdapter


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

        binding.tableNumber.text = getString(R.string.table_number, 15)

        bindAdapters()
        setListeners()
        setObservers()
    }

    private fun bindAdapters() {
        with(binding){

            listAdapter = ArrayAdapter(
                requireContext(),
                R.layout.dishes_category_tab,
                resources.getStringArray(R.array.category_name)
            )
            menuTabs.adapter = listAdapter

            menuTabs.setOnItemClickListener { _, _, position, _ ->
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

                categoryName.text = category
            }

            menuAdapter = DishesMenuAdapter()
            dishesList.adapter = menuAdapter

            menuTabs.setItemChecked(0, true)
            viewModel.getRollsMenu()
        }
    }

    private fun setListeners() {
        with(binding){

            waiterCallingButton.setOnClickListener {

            }
            orderButton.setOnClickListener {

            }
            billButton.setOnClickListener {

            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: DishesMenuState) {
        when (state) {
            is DishesMenuState.Initial,
            is DishesMenuState.Loading      -> renderLoadingState()
            is DishesMenuState.Error        -> renderErrorState()
            is DishesMenuState.Content      -> renderContentState(state.dishesMenu)
        }
    }

    private fun renderLoadingState() {
        binding.progressBar.showWithFade()
        binding.dishesList.hideWithFade()
    }

    private fun renderErrorState() {
        binding.progressBar.hideWithFade()

//        val operationResultDialogFragment =
//            OperationResultDialogFragment.newInstance(
//                isOperationSuccess = DialogOperationType.OPERATION_FAIL,
//                messageId = R.string.booking_error_in_loading,
//                actionButtonTextId = R.string.booking_error_repeat_loading,
//                closeButtonTextId = R.string.booking_error_close,
//                actionRequestCode = DeliveryRequestRequestCode.GET_REQUESTS_FROM_WAREHOUSE.code,
//                closeRequestCode = DeliveryRequestRequestCode.NAVIGATE_BACK.code
//            )
//        showCustomDialog(operationResultDialogFragment)
    }

    private fun renderContentState(dishesMenu: List<Dish>) {
        menuAdapter.setItems(dishesMenu)

        binding.progressBar.hideWithFade()
        binding.dishesList.showWithFade()
    }
}