package ru.nstu.ordsys.features.bill.ui

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
import ru.nstu.ordsys.features.bill.databinding.BillListFragmentBinding
import ru.nstu.ordsys.features.bill.domain.entity.BillItem
import ru.nstu.ordsys.features.bill.presentation.BillListViewModel
import ru.nstu.ordsys.features.bill.presentation.state.BillListState
import ru.nstu.ordsys.features.bill.ui.adapter.BillListAdapter
import ru.nstu.ordsys.operation.ui.DialogOperationType
import ru.nstu.ordsys.operation.ui.OperationResultDialogFragment
import ru.nstu.ordsys.order.domain.entity.Order

class BillListFragment :
    BaseFragment<BillListFragmentBinding>(ru.nstu.ordsys.features.bill.R.layout.bill_list_fragment),
    OperationResultDialogFragment.OperationResultDialogFragmentListener {

    companion object {

        fun newInstance() = BillListFragment()
    }

    private val viewModel: BillListViewModel by viewModel()

    private lateinit var billAdapter: BillListAdapter
    private lateinit var listAdapter: ArrayAdapter<String>

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        BillListFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tableNumber.text = getString(R.string.table_number, 15)

        bindAdapters()
        setListeners()
        setObservers()

        viewModel.loadBill()
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

            billAdapter = BillListAdapter()
            billList.adapter = billAdapter

            billList.layoutManager = LinearLayoutManager(context)
            billList.setDivider(R.drawable.thin_recycler_view_divider)
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
                        actionRequestCode = OperationResultDialogFragment.DialogCloseResults.ACTION.code,
                        closeRequestCode = OperationResultDialogFragment.DialogCloseResults.CLOSE.code
                    )
                showCustomDialog(dialog)
            }
            orderButton.setOnClickListener {
                viewModel.navigateToOrderListScreen()
            }
            cashPayButton.setOnClickListener {
                viewModel.loadBill()
            }
            cardPayButton.setOnClickListener {
                viewModel.loadBill()
            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: BillListState) {
        when (state) {
            is BillListState.Initial,
            is BillListState.Loading -> renderLoadingState()
            is BillListState.Error -> renderErrorState()
            is BillListState.Empty -> renderEmptyState()
            is BillListState.Content -> renderContentState(state.bill)
        }
    }

    private fun renderLoadingState() {
        with(binding) {
            progressBar.showWithFade()
            billScrollView.hideWithFade()
            emptyBillHint.hideWithFade()
            cashPayButton.hideWithFade()
            cardPayButton.hideWithFade()
        }
    }

    private fun renderErrorState() {
        binding.progressBar.hideWithFade()
        binding.emptyBillHint.hideWithFade()

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

    private fun renderEmptyState() {
        with(binding) {
            progressBar.hideWithFade()
            billScrollView.hideWithFade()
            emptyBillHint.showWithFade()
            cashPayButton.hideWithFade()
            cardPayButton.hideWithFade()
        }
    }

    private fun renderContentState(order: List<BillItem>) {
        billAdapter.submitList(order)

        val price = order.sumOf { item -> item.count * item.dish.price }

        with(binding) {
            totalPrice.text = getString(R.string.price_format, price)
            menuHintTotalPrice.text = getString(R.string.price_format, Order.totalPrice.value)
            progressBar.hideWithFade()
            billScrollView.showWithFade()
            emptyBillHint.hideWithFade()
            cashPayButton.showWithFade()
            cardPayButton.showWithFade()
        }
    }

    private fun showToast(textId: Int) {
        val toast: Toast = Toast.makeText(context, textId, Toast.LENGTH_LONG)
        val toastLayout = toast.view as LinearLayout?
        val toastTV = toastLayout!!.getChildAt(0) as TextView
        toastTV.textSize = 22f
        toast.show()
    }

    override fun onDialogCloseResult(requestCode: Int) {
        when (requestCode) {
            OperationResultDialogFragment.DialogCloseResults.ACTION.code -> showToast(ru.nstu.ordsys.component.resources.R.string.waiting_the_waiter)
        }
    }
}