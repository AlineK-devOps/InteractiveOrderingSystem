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

        val thread = Thread {
            try {
                getRollsMenuUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { dishes ->
                            _state.value = DishesListState.Content(dishes)
                        },
                        onError = {
                            _state.value = DishesListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getHotRollsMenu() {
        _state.value = DishesListState.Loading

        val thread = Thread {
            try {
                getHotRollsMenuUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { dishes ->
                            _state.value = DishesListState.Content(dishes)
                        },
                        onError = {
                            _state.value = DishesListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getSushiMenu() {
        _state.value = DishesListState.Loading

        val thread = Thread {
            try {
                getSushiMenuUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { dishes ->
                            _state.value = DishesListState.Content(dishes)
                        },
                        onError = {
                            _state.value = DishesListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getSnacksMenu() {
        _state.value = DishesListState.Loading

        val thread = Thread {
            try {
                getSnacksMenuUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { dishes ->
                            _state.value = DishesListState.Content(dishes)
                        },
                        onError = {
                            _state.value = DishesListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getWokMenu() {
        _state.value = DishesListState.Loading

        val thread = Thread {
            try {
                getWokMenuUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { dishes ->
                            _state.value = DishesListState.Content(dishes)
                        },
                        onError = {
                            _state.value = DishesListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getSoupsMenu() {
        _state.value = DishesListState.Loading

        val thread = Thread {
            try {
                getSoupsMenuUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { dishes ->
                            _state.value = DishesListState.Content(dishes)
                        },
                        onError = {
                            _state.value = DishesListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getDrinksMenu() {
        _state.value = DishesListState.Loading

        val thread = Thread {
            try {
                getDrinksMenuUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { dishes ->
                            _state.value = DishesListState.Content(dishes)
                        },
                        onError = {
                            _state.value = DishesListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getAdditionallyMenu() {
        _state.value = DishesListState.Loading

        val thread = Thread {
            try {
                getAdditionallyMenuUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { dishes ->
                            _state.value = DishesListState.Content(dishes)
                        },
                        onError = {
                            _state.value = DishesListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun navigateToOrderListScreen(){
        router.navigateToOrderListScreen()
    }

    fun navigateToBillListScreen(){
        router.navigateToBillListScreen()
    }
}