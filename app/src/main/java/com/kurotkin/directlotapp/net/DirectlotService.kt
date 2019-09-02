package com.kurotkin.directlotapp.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kurotkin.directlotapp.net.entity.Lot
import kotlinx.coroutines.Deferred
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val CERTIFICATE_SHA256 = "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys="   // from https://www.ssllabs.com/ssltest/analyze.html
const val API_KEY = "temp1jbudbw8b6vexdrtf93dnxaud"
const val URL = "https://kurotkin.com/api/v1/"
const val DOMEN = "kurotkin.com"

interface DirectlotService {

    @GET("last")
    fun getLastLots() : Deferred<List<Lot>>

    companion object {
        operator fun invoke(): DirectlotService{
            val requiredEnterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
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
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DirectlotService::class.java)
        }
    }
}