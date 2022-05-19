package ru.nstu.ordsys.features.waiter.orderlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.nstu.ordsys.component.ui.animation.hideWithFade
import ru.nstu.ordsys.component.ui.animation.showWithFade
import ru.nstu.ordsys.component.ui.fragment.BaseFragment
import ru.nstu.ordsys.features.waiter.orderlist.databinding.WaiterOrderListFragmentBinding
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderListForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.*
import ru.nstu.ordsys.features.waiter.orderlist.presentation.WaiterOrderListViewModel
import ru.nstu.ordsys.features.waiter.orderlist.presentation.state.WaiterOrderListState
import ru.nstu.ordsys.features.waiter.orderlist.ui.adapter.WaiterOrderListAdapter
import ru.nstu.ordsys.shared.user.entity.User

class WaiterOrderListFragment :
    BaseFragment<WaiterOrderListFragmentBinding>(ru.nstu.ordsys.features.waiter.orderlist.R.layout.waiter_order_list_fragment) {

    companion object {

        fun newInstance() = WaiterOrderListFragment()
    }

    private val postDishStatusUseCase: PostDishStatusUseCase by inject()
    private val doneWaiterCallingUseCase: DoneWaiterCallingUseCase by inject()
    private val deleteDishUseCase: DeleteDishUseCase by inject()
    private val deleteOrderUseCase: DeleteOrderUseCase by inject()
    private val doneOrderUseCase: DoneOrderUseCase by inject()

    private val viewModel: WaiterOrderListViewModel by viewModel()

    private lateinit var ordersAdapter: WaiterOrderListAdapter

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        WaiterOrderListFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindAdapters()
        setListeners()
        setObservers()

        binding.userName.text = User.getName()

        viewModel.getOrders()
    }

    private fun bindAdapters() {
        with(binding) {
            ordersAdapter = WaiterOrderListAdapter(
                postDishStatusUseCase,
                doneWaiterCallingUseCase,
                deleteDishUseCase,
                deleteOrderUseCase,
                doneOrderUseCase
            )
            ordersList.adapter = ordersAdapter

            ordersList.layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun setListeners() {
        with(binding) {
            updateButton.setOnClickListener {
                viewModel.getOrders()
            }
            settingsButton.setOnClickListener {
                viewModel.navigateToSettingsScreen()
            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: WaiterOrderListState) {
        when (state) {
            is WaiterOrderListState.Initial,
            is WaiterOrderListState.Loading -> renderLoadingState()
            is WaiterOrderListState.Empty -> renderEmptyState()
            is WaiterOrderListState.Error -> renderErrorState()
            is WaiterOrderListState.Content -> renderContentState(state.ordersList)
        }
    }

    private fun renderEmptyState() {
        binding.progressBar.hideWithFade()
        binding.ordersList.hideWithFade()
        binding.emptyOrdersListHint.showWithFade()
    }

    private fun renderLoadingState() {
        binding.progressBar.showWithFade()
        binding.ordersList.hideWithFade()
        binding.emptyOrdersListHint.hideWithFade()
    }

    private fun renderErrorState() {
        binding.progressBar.hideWithFade()
        binding.emptyOrdersListHint.hideWithFade()

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

    private fun renderContentState(orders: List<OrderListForWaiter>) {
        ordersAdapter.setItems(orders)

        binding.progressBar.hideWithFade()
        binding.emptyOrdersListHint.hideWithFade()
        binding.ordersList.showWithFade()
    }
}