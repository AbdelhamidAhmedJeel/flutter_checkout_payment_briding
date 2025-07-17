package com.example.flutter_checkout_payments_briding
import com.example.flutter_checkout_payments_briding.MainActivity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.Dispatcher

class CheckoutPaymentHandler(
    private val activity: MainActivity
){
    private val publicKey:String? = null;
    private val baseUrl: String = "https://api.sandbox.checkout.com"
    private val  scope = CoroutineScope(Dispatchers.IO+ SupervisorJob())
    private  val  gson = Gson()

}