package com.example.flutter_checkout_payments_briding.network

import android.util.Log
import com.example.flutter_checkout_payments_briding.network.exceptions.ApiExceptions
import com.example.flutter_checkout_payments_briding.network.responses.JeelServerResponse
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
    suspend fun <T : Any> checkoutRequest(call: suspend () -> Response<T>): T {

        try {
            val response = call.invoke()
            if (response.isSuccessful) {
               return response.body() ?: throw ApiExceptions("Empty response body")
            } else {
                val errorBody = response.errorBody()?.string()
                 val message = StringBuilder()
                // ... rest of your error handling
                message.append(errorBody ?: "Unknown error from server") // Provide a default if errorBody is null
                throw ApiExceptions(message.toString())
            }
        } catch (e: Exception) {
            throw ApiExceptions("Network request failed: ${e.message}") // Rethrow as your custom exception or handle differently
        }
    }
}
