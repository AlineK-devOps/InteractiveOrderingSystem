package ru.nstu.ordsys.waiter.data.api

import io.reactivex.Completable
import retrofit2.http.POST
import retrofit2.http.Path

interface WaiterCallApi {

    @POST("/api/waiter_call/{tableId}")
    fun postWaiterCalling(@Path("tableId") tableId: Long): Completable
}