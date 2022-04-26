package ru.nstu.ordsys.order.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.nstu.ordsys.component.resources.R
import ru.nstu.ordsys.component.ui.animation.hideWithFade
import ru.nstu.ordsys.component.ui.animation.showWithFade
import ru.nstu.ordsys.component.ui.dialog.showCustomDialog
import ru.nstu.ordsys.component.ui.fragment.BaseFragment
import ru.nstu.ordsys.component.ui.recyclerview.setDivider
import ru.nstu.ordsys.operation.ui.DialogOperationType
import ru.nstu.ordsys.operation.ui.OperationResultDialogFragment
import ru.nstu.ordsys.order.databinding.OrderListFragmentBinding
import ru.nstu.ordsys.order.domain.entity.Order
import ru.nstu.ordsys.order.presentation.OrderListViewModel
import ru.nstu.ordsys.order.presentation.state.OrderListState
import ru.nstu.ordsys.order.ui.adapter.OrderListAdapter
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class OrderListFragment :
    BaseFragment<OrderListFragmentBinding>(ru.nstu.ordsys.order.R.layout.order_list_fragment),
    OperationResultDialogFragment.OperationResultDialogFragmentListener {

    companion object {

        fun newInstance() = OrderListFragment()
    }

    private val viewModel: OrderListViewModel by viewModel()

    private lateinit var orderAdapter: OrderListAdapter
    private lateinit var listAdapter: ArrayAdapter<String>

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        OrderListFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tableNumber.text = getString(R.string.table_number, 15)

        bindAdapters()
        setListeners()
        setObservers()

        viewModel.loadOrderList()
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
                viewModel.navigateToDishesListScreen(position)
            }

            orderAdapter = OrderListAdapter()
            orderList.adapter = orderAdapter

            orderList.layoutManager = LinearLayoutManager(context)
            orderList.setDivider(R.drawable.thin_recycler_view_divider)
        }
    }

    private fun setListeners() {
        with(binding) {

            waiterCallingButton.setOnClickListener {

                val dialog =
                    OperationResultDialogFragment.newInstance(
                        operationType = DialogOperationType.OPERATION_QUESTION,
                        messageId = R.string.waiter_calling_confirmation,
                        actionButtonTextId = R.string.call_button_text,
                        closeButtonTextId = R.string.cancel_button_text,
                        actionRequestCode = OrderListRequestCode.CALL_WAITER_REQUEST_CODE.code,
                        closeRequestCode = OperationResultDialogFragment.DialogCloseResults.CLOSE.code
                    )
                showCustomDialog(dialog)
            }
            billButton.setOnClickListener {
                viewModel.navigateToBillListScreen()
            }
            clearCartButton.setOnClickListener {

                val dialog =
                    OperationResultDialogFragment.newInstance(
                        operationType = DialogOperationType.OPERATION_QUESTION,
                        messageId = R.string.clear_cart_confirmation,
                        actionButtonTextId = R.string.yes_button_text,
                        closeButtonTextId = R.string.no_button_text,
                        actionRequestCode = OrderListRequestCode.CLEAR_CART_REQUEST_CODE.code,
                        closeRequestCode = OperationResultDialogFragment.DialogCloseResults.CLOSE.code
                    )
                showCustomDialog(dialog)
            }
            sendOrderButton.setOnClickListener {

                val dialog =
                    OperationResultDialogFragment.newInstance(
                        operationType = DialogOperationType.OPERATION_QUESTION,
                        messageId = R.string.send_order_confirmation,
                        actionButtonTextId = R.string.yes_button_text,
                        closeButtonTextId = R.string.no_button_text,
                        actionRequestCode = OrderListRequestCode.SEND_ORDER_REQUEST_CODE.code,
                        closeRequestCode = OperationResultDialogFragment.DialogCloseResults.CLOSE.code
                    )
                showCustomDialog(dialog)
            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        Order.totalPrice.observe(viewLifecycleOwner, ::totalPriceChanged)
        Order.orderLiveData.observe(viewLifecycleOwner, ::renderContentState)
    }

    private fun handleState(state: OrderListState) {
        when (state) {
            is OrderListState.Initial,
            is OrderListState.Loading -> renderLoadingState()
            is OrderListState.Error -> renderErrorState()
            is OrderListState.Content -> renderContentState(state.order)
        }
    }

    private fun renderLoadingState() {
        with(binding) {
            progressBar.showWithFade()
            orderScrollView.hideWithFade()
            emptyOrderHint.hideWithFade()
        }
    }

    private fun renderErrorState() {
        binding.progressBar.hideWithFade()
        binding.emptyOrderHint.hideWithFade()

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

    private fun renderContentState(order: HashMap<Dish, Int>) {
        if (order.isEmpty())
            showEmptyOrderHint()
        else {
            orderAdapter.setItems(order)
            showOrderList()
        }
    }

    private fun totalPriceChanged(price: Int) {
        binding.menuHintTotalPrice.text = getString(R.string.price_format, price)
        binding.totalPrice.text = getString(R.string.price_format, price)
    }

    private fun showToast(textId: Int) {
        val toast: Toast = Toast.makeText(context, textId, Toast.LENGTH_LONG)
        val toastLayout = toast.view as LinearLayout?
        val toastTV = toastLayout!!.getChildAt(0) as TextView
        toastTV.textSize = 22f
        toast.show()
    }

    private fun showOrderList() {
        with(binding) {
            progressBar.hideWithFade()
            orderScrollView.showWithFade()
            emptyOrderHint.hideWithFade()
            sendOrderButton.showWithFade()
            clearCartButton.showWithFade()
        }
    }

    private fun showEmptyOrderHint() {
        with(binding) {
            progressBar.hideWithFade()
            orderScrollView.hideWithFade()
            emptyOrderHint.showWithFade()
            sendOrderButton.hideWithFade()
            clearCartButton.hideWithFade()
        }
    }

    override fun onDialogCloseResult(requestCode: Int) {
        when (requestCode) {
            OrderListRequestCode.CALL_WAITER_REQUEST_CODE.code -> showToast(ru.nstu.ordsys.component.resources.R.string.waiting_the_waiter)
            OrderListRequestCode.CLEAR_CART_REQUEST_CODE.code -> viewModel.clearCart()
            OrderListRequestCode.SEND_ORDER_REQUEST_CODE.code -> viewModel.sendOrder()
        }
    }
}