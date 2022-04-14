package ru.nstu.ordsys.mockapiserver.interceptor

import android.content.Context
import android.net.Uri
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import ru.nstu.ordsys.mockapiserver.assets.error404
import ru.nstu.ordsys.mockapiserver.assets.getFake
import ru.nstu.ordsys.mockapiserver.assets.postFake

class FakeDataInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.toString()
        val uri = Uri.parse(url)
        val response = Response.Builder()
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .addHeader("content-type", "application/json")
        return when (chain.request().method) {
            "GET"  -> getFake(context, uri, response)
            "POST" -> postFake(uri, response, chain)
            else   -> error404(response)
        }.build()
    }
}