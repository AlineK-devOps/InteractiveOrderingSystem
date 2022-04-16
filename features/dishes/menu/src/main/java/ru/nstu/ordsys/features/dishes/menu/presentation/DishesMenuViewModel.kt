package ru.nstu.ordsys.features.dishes.menu.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.dishes.menu.domain.usecase.*
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class DishesMenuViewModel(
    private val getAdditionallyMenuUseCase: GetAdditionallyMenuUseCase,
    private val getDrinksMenuUseCase: GetDrinksMenuUseCase,
    private val getHotRollsMenuUseCase: GetHotRollsMenuUseCase,
    private val getRollsMenuUseCase: GetRollsMenuUseCase,
    private val getSnacksMenuUseCase: GetSnacksMenuUseCase,
    private val getSoupsMenuUseCase: GetSoupsMenuUseCase,
    private val getSushiMenuUseCase: GetSushiMenuUseCase,
    private val getWokMenuUseCase: GetWokMenuUseCase,
    private val router: DishesMenuRouter
    ) : BaseViewModel() {

    private var _state = MutableLiveData<DishesMenuState>(DishesMenuState.Initial)
    val state: LiveData<DishesMenuState> = _state

    fun getRollsMenu() {
        _state.value = DishesMenuState.Loading
        getRollsMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesMenuState.Content(vacations)
                },
                onError = {
                    _state.value = DishesMenuState.Error
                }
            )
            .addToDisposableList()
    }

    fun getHotRollsMenu() {
        _state.value = DishesMenuState.Loading
        getHotRollsMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesMenuState.Content(vacations)
                },
                onError = {
                    _state.value = DishesMenuState.Error
                }
            )
            .addToDisposableList()
    }

    fun getSushiMenu() {
        _state.value = DishesMenuState.Loading
        getSushiMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesMenuState.Content(vacations)
                },
                onError = {
                    _state.value = DishesMenuState.Error
                }
            )
            .addToDisposableList()
    }

    fun getSnacksMenu() {
        _state.value = DishesMenuState.Loading
        getSnacksMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesMenuState.Content(vacations)
                },
                onError = {
                    _state.value = DishesMenuState.Error
                }
            )
            .addToDisposableList()
    }

    fun getWokMenu() {
        _state.value = DishesMenuState.Loading
        getWokMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesMenuState.Content(vacations)
                },
                onError = {
                    _state.value = DishesMenuState.Error
                }
            )
            .addToDisposableList()
    }

    fun getSoupsMenu() {
        _state.value = DishesMenuState.Loading
        getSoupsMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesMenuState.Content(vacations)
                },
                onError = {
                    _state.value = DishesMenuState.Error
                }
            )
            .addToDisposableList()
    }

    fun getDrinksMenu() {
        _state.value = DishesMenuState.Loading
        getDrinksMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesMenuState.Content(vacations)
                },
                onError = {
                    _state.value = DishesMenuState.Error
                }
            )
            .addToDisposableList()
    }

    fun getAdditionallyMenu() {
        _state.value = DishesMenuState.Loading
        getAdditionallyMenuUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { vacations ->
                    _state.value = DishesMenuState.Content(vacations)
                },
                onError = {
                    _state.value = DishesMenuState.Error
                }
            )
            .addToDisposableList()
    }

//    fun navigateExit() {
//        router.exit()
//    }
}