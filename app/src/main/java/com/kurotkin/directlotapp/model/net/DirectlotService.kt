package com.kurotkin.directlotapp.model.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kurotkin.directlotapp.domain.crypto.CryptoPouch
import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import com.kurotkin.directlotapp.model.net.entity.LotLiteFromServer
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val CERTIFICATE_SHA256 = "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys="   // from https://www.ssllabs.com/ssltest/analyze.html
const val URL = "https://kurotkin.com/api/v1/"
const val DOMEN = "kurotkin.com"

interface DirectlotService {

    @GET("last")
    fun getLastLots() : Single<List<LotFromServer>>

    @GET("last/lite")
    fun getLastLiteLots() : Single<List<LotLiteFromServer>>

    @GET("one")
    fun getOneLot(@Query("id") id : Long) : Single<LotFromServer>

    companion object {
        operator fun invoke(): DirectlotService{
            val requiredEnterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", CryptoPouch.getToken())
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val certificationPinner = CertificatePinner.Builder()
                .add(DOMEN, CERTIFICATE_SHA256)
                .build()


            val okHttpClient = OkHttpClient.Builder()
                .certificatePinner(certificationPinner)
                .addInterceptor(requiredEnterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DirectlotService::class.java)
        }
    }
}