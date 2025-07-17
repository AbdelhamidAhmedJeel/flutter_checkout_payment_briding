package com.example.flutter_checkout_payments_briding.network

import com.example.flutter_checkout_payments_briding.utils.JeelServerResponse
import retrofit2.Response
import retrofit2.http.POST

interface PaymentsRequestsInterface {
    @POST("payments/orders")
    suspend fun sendOrderInformation(): Response<JeelServerResponse>
}