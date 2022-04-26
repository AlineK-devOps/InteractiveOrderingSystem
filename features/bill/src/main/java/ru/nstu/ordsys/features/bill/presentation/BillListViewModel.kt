package ru.nstu.ordsys.features.bill.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.bill.domain.usecase.GetBillListUseCase
import ru.nstu.ordsys.features.bill.presentation.state.BillListState

class BillListViewModel(
    private val useCase: GetBillListUseCase,
    private val router: BillListRouter
) : BaseViewModel() {

    private var _state = MutableLiveData<BillListState>(BillListState.Initial)
    val state: LiveData<BillListState> = _state

    fun loadBill() {
        _state.value = BillListState.Loading

        useCase(15)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { bill ->
                    if (bill.isNullOrEmpty())
                        _state.value = BillListState.Empty
                    else
                        _state.value = BillListState.Content(bill)
                },
                onError = {
                    _state.value = BillListState.Error
                }
            )
            .addToDisposableList()
    }

    fun navigateToDishesListScreen(position: Int) {
        router.navigateToDishesListScreen(position)
    }

    fun navigateToOrderListScreen(){
        router.navigateToOrderListScreen()
    }
}