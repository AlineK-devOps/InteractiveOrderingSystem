package ru.nstu.ordsys.di

import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

//val networkModule = module {
//    single { provideLoggingInterceptor() }
//    single { NetworkChanger(androidContext()) }
//    single { FakeDataInterceptor(androidContext()) }
//    single(named(ORIGINAL)) {
//        provideOkHttpClient(
//            interceptors = listOf(
//                get<HttpLoggingInterceptor>()
//            )
//        )
//    }
//    single(named(MOCK)) {
//        provideOkHttpClient(
//            interceptors = listOf(
//                get<FakeDataInterceptor>()
//            )
//        )
//    }
//    single(named(ORIGINAL)) {
//        provideRetrofit(
//            okHttpClient = get(named(ORIGINAL)),
//            url = "нужен бэк спасите"
//        )
//    }
//    single(named(MOCK)) {
//        provideRetrofit(
//            okHttpClient = get(named(MOCK)),
//            url = "https://mock"
//        )
//    }
//}