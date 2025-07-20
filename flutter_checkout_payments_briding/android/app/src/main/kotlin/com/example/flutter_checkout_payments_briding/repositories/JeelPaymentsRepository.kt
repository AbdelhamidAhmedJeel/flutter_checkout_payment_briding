package com.example.flutter_checkout_payments_briding.repositories

import android.util.Log
import com.example.flutter_checkout_payments_briding.models.request_parameters.PaymentSessionParams
import com.example.flutter_checkout_payments_briding.network.RetrofitApi
import com.example.flutter_checkout_payments_briding.network.SafeApiRequest
import com.example.flutter_checkout_payments_briding.network.responses.JeelServerResponse
import com.example.flutter_checkout_payments_briding.network.responses.PaymentSessionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

private const val TAG = "JeelPaymentsRepository"
class JeelPaymentsRepository @Inject constructor(private val retrofitApi: RetrofitApi):
    SafeApiRequest() {

    suspend fun createPaymentSession(params: PaymentSessionParams, headers: Map<String, String>): PaymentSessionResponse{
         return checkoutRequest { retrofitApi.providePaymentsRequestInterface().createPaymentSession(headers,params) }
    }
}