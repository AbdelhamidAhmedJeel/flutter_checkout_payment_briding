package com.example.flutter_checkout_payments_briding

import com.example.flutter_checkout_payments_briding.models.request_parameters.Address
import com.example.flutter_checkout_payments_briding.models.request_parameters.Billing
import com.example.flutter_checkout_payments_briding.models.request_parameters.Customer
import com.example.flutter_checkout_payments_briding.models.request_parameters.PaymentSessionParams
import com.example.flutter_checkout_payments_briding.repositories.JeelPaymentsRepository

import com.google.gson.Gson
import io.flutter.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "CheckoutPaymentHandler"

@Singleton
class CheckoutPaymentHandler @Inject constructor(
    private val jeelPaymentsRepository: JeelPaymentsRepository,

    ) {

    private var publicKey: String? = null;
    private val baseUrl: String = "https://api.sandbox.checkout.com"
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val gson = Gson()


    fun handleMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "initialize" -> initialize(call, result)
            "createPaymentSession" -> createPaymentSession(call, result)
        }
    }

    private fun initialize(call: MethodCall, result: MethodChannel.Result) {
        try {
            val incomingKey = call.argument<String>("publicKey")
            val isProduction = call.argument<Boolean>("isProduction")
            if (incomingKey == null) {
                result.error("Invalid Arguments", "public key is required", null)
                return
            }
            publicKey = incomingKey

            result.success(true)

        } catch (e: Exception) {
            result.error("Initialization error", e.localizedMessage, null)
        }
    }

    private fun createPaymentSession(call: MethodCall, result: MethodChannel.Result) {
        if (publicKey == null) {
            result.error("Invalid Arguments", "public key is required", null)
            return
        }
        scope.launch {
            Log.d(TAG, "Coroutine started for createPaymentSession")

            try {

                val amount = call.argument<Int>("amount")
                val currency = call.argument<String>("currency")
                val name = call.argument<String>("name")
                val email = call.argument<String>("email")
                val address = call.argument<String>("address")
                val country = call.argument<String>("country")
                val channelId = call.argument<String>("channelId")

                val headers = mapOf(
                    "Authorization" to "Bearer $publicKey",
                    "Content-Type" to "application/json", // ← MISSING in your log
                    "Accept" to "application/json"
                )

                val params = PaymentSessionParams(
                    processingChannelId = channelId!!,
                    amount = amount!!,
                    currency = currency!!,
                    reference = "ORD-123A",
                    billing = Billing(Address(country!!)),
                    customer = Customer(
                        name!!, email!!
                    ),
                    successUrl = "https://example.com/success",
                    failureUrl = "https://example.com/failure"

                )
                Log.d(TAG, params.toString())

                jeelPaymentsRepository.createPaymentSession(params, headers).let { response ->
                    Log.d(TAG, "API Response Code: ${response}")


                    // Don't convert to JSON string, just pass the response body object
                    withContext(Dispatchers.Main) {
                        result.success(gson.toJson(response))
                    }
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) { // Ensure this runs on the Main thread
                    Log.d(TAG, e.toString())
                    // Similarly, ensure result.error is called on the Main thread.
                    result.error("Payment session error", e.localizedMessage, null)

                }
            }
        }


    }
}