package com.example.flutter_checkout_payments_briding.network

import com.example.flutter_checkout_payments_briding.utils.ApiExceptions
import com.example.flutter_checkout_payments_briding.utils.JeelServerResponse
import com.google.gson.Gson
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body() ?: throw ApiExceptions("Empty response body")
        } else {
            val errorBody = response.errorBody()?.string()
            val message = StringBuilder()

            try {
                val errorResponse = Gson().fromJson(errorBody, JeelServerResponse::class.java)
                errorResponse.errors?.let {
                    it.forEach { error ->
                        message.append("${error.description}\n")
                    }
                }  ?: message.append("Unknown error occurred.")
            } catch (e: Exception) {
                message.append("Error parsing error response")
            }

            throw ApiExceptions(message.toString())
        }
    }
}
