package com.example.flutter_checkout_payments_briding

import com.example.flutter_checkout_payments_briding.modules.JeelApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [JeelApplicationModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}