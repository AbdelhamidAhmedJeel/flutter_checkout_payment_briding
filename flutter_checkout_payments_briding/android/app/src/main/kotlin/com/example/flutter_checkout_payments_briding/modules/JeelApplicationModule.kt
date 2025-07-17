package com.example.flutter_checkout_payments_briding.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JeelApplicationModule(private val  context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context{
        return  context;
    }
}