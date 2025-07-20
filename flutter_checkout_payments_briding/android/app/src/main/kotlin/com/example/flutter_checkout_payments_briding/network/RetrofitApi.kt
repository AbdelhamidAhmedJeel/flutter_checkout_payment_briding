package com.example.flutter_checkout_payments_briding.network

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "RetrofitApi"
@Module
class RetrofitApi @Inject constructor(private var networkInterceptor: JeelNetworkInterceptor) {

    private lateinit var retrofit: Retrofit

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        if (!::retrofit.isInitialized) {


            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            retrofit = Retrofit.Builder()
                .baseUrl("https://api.sandbox.checkout.com/")
                .client(
                    OkHttpClient.Builder()
                        .readTimeout(100, TimeUnit.SECONDS)
                        .connectTimeout(100, TimeUnit.SECONDS)
                        .addInterceptor(networkInterceptor)
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit
    }

    @Provides
    @Singleton
    fun providePaymentsRequestInterface(): PaymentsRequestsInterface {
        return provideRetrofit().create(PaymentsRequestsInterface::class.java)
    }
}
