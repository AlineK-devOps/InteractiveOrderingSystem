package ru.nstu.ordsys.waiter.domain.repository

import io.reactivex.Completable

interface WaiterCallRepository {

    fun postWaiterCalling(tableId: Long): Completable
}