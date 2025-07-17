package com.example.flutter_checkout_payments_briding.network

import com.google.gson.GsonBuilder
import com.suecal.suecalcrmnewkmm.data.network.JeelNetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RetrofitApi @Inject constructor(private var networkInterceptor: JeelNetworkInterceptor) {
    private var retrofit: Retrofit? = null

    @Provides
    @Singleton
    fun  getRetrofitApiSandbox(): Retrofit?{
        if(retrofit ==null){
            val gson = GsonBuilder().create()
            retrofit = Retrofit.Builder().baseUrl("https://").client(OkHttpClient.Builder().readTimeout(100,
                TimeUnit.SECONDS).connectTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(networkInterceptor).build()).addConverterFactory(
                GsonConverterFactory.create(gson)).build()
        }
        return retrofit
    }
}