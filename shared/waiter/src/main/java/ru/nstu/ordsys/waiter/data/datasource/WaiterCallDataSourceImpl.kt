package ru.nstu.ordsys.waiter.data.datasource

import io.reactivex.Completable
import ru.nstu.ordsys.waiter.data.api.WaiterCallApi

class WaiterCallDataSourceImpl(private val api: WaiterCallApi) : WaiterCallDataSource {

    override fun postWaiterCalling(tableId: Long): Completable =
        api.postWaiterCalling(tableId)
}