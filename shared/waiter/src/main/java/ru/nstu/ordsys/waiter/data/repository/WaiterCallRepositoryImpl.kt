package ru.nstu.ordsys.waiter.data.repository

import io.reactivex.Completable
import ru.nstu.ordsys.waiter.data.datasource.WaiterCallDataSource
import ru.nstu.ordsys.waiter.domain.repository.WaiterCallRepository

class WaiterCallRepositoryImpl(
    private val dataSource: WaiterCallDataSource
) : WaiterCallRepository {

    override fun postWaiterCalling(tableId: Long): Completable =
        dataSource.postWaiterCalling(tableId)
}