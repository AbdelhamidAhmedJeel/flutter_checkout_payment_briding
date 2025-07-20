package com.example.flutter_checkout_payments_briding.network

import com.example.flutter_checkout_payments_briding.models.request_parameters.PaymentSessionParams
import com.example.flutter_checkout_payments_briding.network.responses.JeelServerResponse
import com.example.flutter_checkout_payments_briding.network.responses.PaymentSessionResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST

interface PaymentsRequestsInterface {

    @POST("payment-sessions")
    suspend fun createPaymentSession(@HeaderMap myHeaders: Map<String, String>, @Body body: PaymentSessionParams): Response<PaymentSessionResponse>
}