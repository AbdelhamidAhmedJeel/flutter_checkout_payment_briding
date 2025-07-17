package com.example.flutter_checkout_payments_briding

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.flutter_checkout_payments_briding.modules.JeelApplicationModule

class JeelApp : Application() {
    lateinit var appComponent: AppComponent

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initializeAppComponent() {
        appComponent =
            DaggerAppComponent.builder().jeelApplicationModule(JeelApplicationModule(this))
                // Example if your module needs the app instance
                .build()

    }
}