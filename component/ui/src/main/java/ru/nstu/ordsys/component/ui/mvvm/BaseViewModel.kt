package ru.nstu.ordsys.component.ui.mvvm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposableList = CompositeDisposable()

    fun Disposable.addToDisposableList() {
        disposableList.add(this)
    }

    override fun onCleared() {
        disposableList.dispose()
        super.onCleared()
    }
}