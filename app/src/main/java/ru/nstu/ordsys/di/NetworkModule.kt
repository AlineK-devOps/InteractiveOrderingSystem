package ru.nstu.ordsys.di

import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.nstu.ordsys.mockapiserver.changer.MOCK
import ru.nstu.ordsys.mockapiserver.changer.NetworkChanger
import ru.nstu.ordsys.mockapiserver.changer.ORIGINAL
import ru.nstu.ordsys.mockapiserver.interceptor.FakeDataInterceptor
import ru.nstu.ordsys.network.interceptor.provideLoggingInterceptor
import ru.nstu.ordsys.network.okhttp.provideOkHttpClient
import ru.nstu.ordsys.network.retrofit.provideRetrofit

val networkModule = module {
    single { provideLoggingInterceptor() }
    single { NetworkChanger(androidContext()) }
    single { FakeDataInterceptor(androidContext()) }
    single(named(ORIGINAL)) {
        provideOkHttpClient(
            interceptors = listOf(
                get<HttpLoggingInterceptor>()
            )
        )
    }
    single(named(MOCK)) {
        provideOkHttpClient(
            interceptors = listOf(
                get<FakeDataInterceptor>()
            )
        )
    }
    single(named(ORIGINAL)) {
        provideRetrofit(
            okHttpClient = get(named(ORIGINAL)),
            url = "http://10.0.2.2:8080"
        )
    }
    single(named(MOCK)) {
        provideRetrofit(
            okHttpClient = get(named(MOCK)),
            url = "http://mock"
        )
    }
}