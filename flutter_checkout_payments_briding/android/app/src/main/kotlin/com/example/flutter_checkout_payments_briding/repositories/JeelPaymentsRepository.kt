package com.example.flutter_checkout_payments_briding.repositories

import com.example.flutter_checkout_payments_briding.network.RetrofitApi
import com.example.flutter_checkout_payments_briding.network.SafeApiRequest
import com.example.flutter_checkout_payments_briding.utils.JeelServerResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import javax.inject.Inject

class JeelPaymentsRepository @Inject constructor(private val retrofitApi: RetrofitApi):
    SafeApiRequest() {

    suspend fun sendOrderInformation(price:String): JeelServerResponse{
        val body: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("price",price).build()
        return apiRequest { retrofitApi.getPaymentsRequestInterface().sendOrderInformation(body) }
    }
}