package ru.nstu.ordsys.features.bill.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nstu.ordsys.features.bill.data.model.BillListModel

interface BillListApi {

    @GET("/api/bill/{id}")
    fun getBillList(@Path("id") tableId: Long): Single<BillListModel>
}