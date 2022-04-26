package ru.nstu.ordsys.features.dishes.menu.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.dishes.menu.domain.usecase.*
import ru.nstu.ordsys.features.dishes.menu.presentation.state.DishesListState

class DishesListViewModel(
    private val getAdditionallyMenuUseCase: GetAdditionallyMenuUseCase,
    private val getDrinksMenuUseCase: GetDrinksMenuUseCase,
    private val getHotRollsMenuUseCase: GetHotRollsMenuUseCase,
    private val getRollsMenuUseCase: GetRollsMenuUseCase,
    private val getSnacksMenuUseCase: GetSnacksMenuUseCase,
    private val getSoupsMenuUseCase: GetSoupsMenuUseCase,
    private val getSushiMenuUseCase: GetSushiMenuUseCase,
    private val getWokMenuUseCase: GetWokMenuUseCase,
    private val router: DishesListRouter
    ) : BaseViewModel() {

    private var _state = MutableLiveData<DishesListState>(DishesListState.Initial)
    val state: LiveData<DishesListState> = _state

    fun getRollsMenu() {
        _state.value = DishesListState.Loading
        getRollsMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesListState.Content(vacations)
                },
                onError = {
                    _state.value = DishesListState.Error
                }
            )
            .addToDisposableList()
    }

    fun getHotRollsMenu() {
        _state.value = DishesListState.Loading
        getHotRollsMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesListState.Content(vacations)
                },
                onError = {
                    _state.value = DishesListState.Error
                }
            )
            .addToDisposableList()
    }

    fun getSushiMenu() {
        _state.value = DishesListState.Loading
        getSushiMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesListState.Content(vacations)
                },
                onError = {
                    _state.value = DishesListState.Error
                }
            )
            .addToDisposableList()
    }

    fun getSnacksMenu() {
        _state.value = DishesListState.Loading
        getSnacksMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesListState.Content(vacations)
                },
                onError = {
                    _state.value = DishesListState.Error
                }
            )
            .addToDisposableList()
    }

    fun getWokMenu() {
        _state.value = DishesListState.Loading
        getWokMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesListState.Content(vacations)
                },
                onError = {
                    _state.value = DishesListState.Error
                }
            )
            .addToDisposableList()
    }

    fun getSoupsMenu() {
        _state.value = DishesListState.Loading
        getSoupsMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesListState.Content(vacations)
                },
                onError = {
                    _state.value = DishesListState.Error
                }
            )
            .addToDisposableList()
    }

    fun getDrinksMenu() {
        _state.value = DishesListState.Loading
        getDrinksMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesListState.Content(vacations)
                },
                onError = {
                    _state.value = DishesListState.Error
                }
            )
            .addToDisposableList()
    }

    fun getAdditionallyMenu() {
        _state.value = DishesListState.Loading
        getAdditionallyMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesListState.Content(vacations)
                },
                onError = {
                    _state.value = DishesListState.Error
                }
            )
            .addToDisposableList()
    }

    fun navigateToOrderListScreen(){
        router.navigateToOrderListScreen()
    }

    fun navigateToBillListScreen(){
        router.navigateToBillListScreen()
    }
}