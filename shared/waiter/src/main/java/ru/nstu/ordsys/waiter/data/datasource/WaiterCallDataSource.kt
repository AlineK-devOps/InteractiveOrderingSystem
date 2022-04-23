package ru.nstu.ordsys.waiter.data.datasource

import io.reactivex.Completable

interface WaiterCallDataSource {

    fun postWaiterCalling(tableId: Long): Completable
}