package com.example.flutter_checkout_payments_briding

import com.example.flutter_checkout_payments_briding.modules.JeelApplicationModule
import com.example.flutter_checkout_payments_briding.network.RetrofitApi
import com.example.flutter_checkout_payments_briding.repositories.JeelPaymentsRepository
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(modules = [RetrofitApi::class, JeelApplicationModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun getCheckoutPaymentHandler(): CheckoutPaymentHandler

}