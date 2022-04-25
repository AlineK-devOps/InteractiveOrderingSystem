package ru.nstu.ordsys.features.dishes.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.nstu.ordsys.component.ui.animation.hideWithFade
import ru.nstu.ordsys.component.ui.animation.showWithFade
import ru.nstu.ordsys.component.ui.dialog.showCustomDialog
import ru.nstu.ordsys.component.ui.fragment.BaseFragment
import ru.nstu.ordsys.component.ui.recyclerview.setDivider
import ru.nstu.ordsys.component.resources.R
import ru.nstu.ordsys.features.dishes.menu.databinding.DishesListFragmentBinding
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesListViewModel
import ru.nstu.ordsys.features.dishes.menu.presentation.state.DishesListState
import ru.nstu.ordsys.features.dishes.menu.ui.adapter.DishesListAdapter
import ru.nstu.ordsys.operation.ui.DialogOperationType
import ru.nstu.ordsys.operation.ui.OperationResultDialogFragment
import ru.nstu.ordsys.order.domain.entity.Order
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish


class DishesListFragment(val position: Int)
    : BaseFragment<DishesListFragmentBinding>(ru.nstu.ordsys.features.dishes.menu.R.layout.dishes_list_fragment),
    OperationResultDialogFragment.OperationResultDialogFragmentListener {

    companion object {

        fun newInstance(position: Int) = DishesListFragment(position)
    }

    private val viewModel: DishesListViewModel by viewModel()

    private lateinit var menuAdapter: DishesListAdapter

    private lateinit var listAdapter: ArrayAdapter<String>

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DishesListFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tableNumber.text = getString(ru.nstu.ordsys.component.resources.R.string.table_number, 15)

        bindAdapters()
        setListeners()
        setObservers()
    }

    private fun bindAdapters() {
        with(binding) {

            listAdapter = ArrayAdapter(
                requireContext(),
                ru.nstu.ordsys.features.dishes.menu.R.layout.dishes_category_tab,
                resources.getStringArray(R.array.category_name)
            )
            menuTabs.adapter = listAdapter

            menuTabs.setOnItemClickListener { _, _, position, _ ->
                val category = resources.getStringArray(R.array.category_name)[position]

                when (category) {
                    "Роллы" -> viewModel.getRollsMenu()
                    "Горячие роллы" -> viewModel.getHotRollsMenu()
                    "Суши" -> viewModel.getSushiMenu()
                    "Салаты и закуски" -> viewModel.getSnacksMenu()
                    "Вок" -> viewModel.getWokMenu()
                    "Супы" -> viewModel.getSoupsMenu()
                    "Напитки" -> viewModel.getDrinksMenu()
                    "Дополнительно" -> viewModel.getAdditionallyMenu()
                }

                categoryName.text = category
            }

            menuAdapter = DishesListAdapter()
            dishesList.adapter = menuAdapter

            dishesList.layoutManager = GridLayoutManager(context, 3)
            dishesList.setDivider(R.drawable.bold_recycler_view_divider)

            menuTabs.setItemChecked(position, true)
            when (position) {
                0 -> viewModel.getRollsMenu()
                1 -> viewModel.getHotRollsMenu()
                2 -> viewModel.getSushiMenu()
                3 -> viewModel.getSnacksMenu()
                4 -> viewModel.getWokMenu()
                5 -> viewModel.getSoupsMenu()
                6 -> viewModel.getDrinksMenu()
                7 -> viewModel.getAdditionallyMenu()
            }
        }
    }

    private fun setListeners() {
        with(binding) {

            waiterCallingButton.setOnClickListener {

                val dialog =
                    OperationResultDialogFragment.newInstance(
                        operationType = DialogOperationType.OPERATION_QUESTION,
                        messageId = ru.nstu.ordsys.component.resources.R.string.waiter_calling_confirmation,
                        actionButtonTextId = ru.nstu.ordsys.component.resources.R.string.call_button_text,
                        closeButtonTextId = ru.nstu.ordsys.component.resources.R.string.cancel_button_text,
                        actionRequestCode = OperationResultDialogFragment.DialogCloseResults.ACTION.code,
                        closeRequestCode = OperationResultDialogFragment.DialogCloseResults.CLOSE.code
                    )
                showCustomDialog(dialog)
            }
            orderButton.setOnClickListener {
                viewModel.navigateToOrderListScreen()
            }
            billButton.setOnClickListener {

            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        Order.totalPrice.observe(viewLifecycleOwner, ::totalPriceChanged)
    }

    private fun handleState(state: DishesListState) {
        when (state) {
            is DishesListState.Initial,
            is DishesListState.Loading -> renderLoadingState()
            is DishesListState.Error -> renderErrorState()
            is DishesListState.Content -> renderContentState(state.dishesMenu)
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

    private fun totalPriceChanged(price: Int) {
        binding.totalPrice.text = getString(R.string.price_format, price)
    }

    private fun showToast(textId: Int){
        val toast: Toast = Toast.makeText(context, textId, Toast.LENGTH_LONG)
        val toastLayout = toast.view as LinearLayout?
        val toastTV = toastLayout!!.getChildAt(0) as TextView
        toastTV.textSize = 22f
        toast.show()
    }

    override fun onDialogCloseResult(requestCode: Int) {
        when (requestCode){
            OperationResultDialogFragment.DialogCloseResults.ACTION.code -> showToast(ru.nstu.ordsys.component.resources.R.string.waiting_the_waiter)
        }
    }
}