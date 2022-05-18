package ru.nstu.ordsys.features.cook.orderlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import ru.nstu.ordsys.component.resources.R
import ru.nstu.ordsys.component.ui.animation.hideWithFade
import ru.nstu.ordsys.component.ui.animation.showWithFade
import ru.nstu.ordsys.component.ui.fragment.BaseFragment
import ru.nstu.ordsys.component.ui.recyclerview.setDivider
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.nstu.ordsys.features.cook.orderlist.databinding.CookOrderListFragmentBinding
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderListForCook
import ru.nstu.ordsys.features.cook.orderlist.presentation.CookOrderListViewModel
import ru.nstu.ordsys.features.cook.orderlist.presentation.state.CookOrderListState
import ru.nstu.ordsys.features.cook.orderlist.ui.adapter.CookOrderListAdapter

class CookOrderListFragment :
    BaseFragment<CookOrderListFragmentBinding>(ru.nstu.ordsys.features.cook.orderlist.R.layout.cook_order_list_fragment) {

    companion object {

        fun newInstance() = CookOrderListFragment()
    }

    private val viewModel: CookOrderListViewModel by viewModel()

    private lateinit var ordersAdapter: CookOrderListAdapter

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        CookOrderListFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindAdapters()
        setListeners()
        setObservers()

        viewModel.getOrders()
        binding.allButton.setBackgroundColor(resources.getColor(R.color.orange))
    }

    private fun bindAdapters() {
        with(binding) {
            ordersAdapter = CookOrderListAdapter()
            ordersList.adapter = ordersAdapter

            ordersList.layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun setListeners() {
        with(binding) {
            allButton.setOnClickListener {
                viewModel.getOrders()
                viewModel.updateTab("ALL")
                offTabs()
                allButton.setBackgroundColor(resources.getColor(R.color.orange))
            }
            hotWorkshopButton.setOnClickListener {
                viewModel.getHotWorkshopOrders()
                viewModel.updateTab("HOT_WORKSHOP")
                offTabs()
                hotWorkshopButton.setBackgroundColor(resources.getColor(R.color.orange))
            }
            coldWorkshopButton.setOnClickListener {
                viewModel.getColdWorkshopOrders()
                viewModel.updateTab("COLD_WORKSHOP")
                offTabs()
                coldWorkshopButton.setBackgroundColor(resources.getColor(R.color.orange))
            }
            barButton.setOnClickListener {
                viewModel.getBarOrders()
                viewModel.updateTab("BAR")
                offTabs()
                barButton.setBackgroundColor(resources.getColor(R.color.orange))
            }
            updateButton.setOnClickListener {
                viewModel.updateOrders()
            }
            settingsButton.setOnClickListener {
                viewModel.navigateToSettingsScreen()
            }
        }
    }

    private fun offTabs(){
        with(binding){
            allButton.setBackgroundColor(resources.getColor(R.color.blue_gray))
            hotWorkshopButton.setBackgroundColor(resources.getColor(R.color.blue_gray))
            coldWorkshopButton.setBackgroundColor(resources.getColor(R.color.blue_gray))
            barButton.setBackgroundColor(resources.getColor(R.color.blue_gray))
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: CookOrderListState) {
        when (state) {
            is CookOrderListState.Initial,
            is CookOrderListState.Loading -> renderLoadingState()
            is CookOrderListState.Empty -> renderEmptyState()
            is CookOrderListState.Error -> renderErrorState()
            is CookOrderListState.Content -> renderContentState(state.ordersList)
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

    private fun renderContentState(orders: List<OrderListForCook>) {
        ordersAdapter.setItems(orders)

        binding.progressBar.hideWithFade()
        binding.emptyOrdersListHint.hideWithFade()
        binding.ordersList.showWithFade()
    }
}