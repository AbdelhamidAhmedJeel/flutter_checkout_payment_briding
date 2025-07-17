package com.example.flutter_checkout_payments_briding.network

import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>): T {

    }
}