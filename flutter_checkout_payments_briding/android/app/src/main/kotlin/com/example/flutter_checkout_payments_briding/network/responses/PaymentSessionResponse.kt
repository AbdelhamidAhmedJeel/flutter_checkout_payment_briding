package com.example.flutter_checkout_payments_briding.network.responses

import com.google.gson.annotations.SerializedName

data class PaymentSessionResponse (
    @SerializedName("id") var id:String,
    @SerializedName("payment_session_secret") var paymentSessionSecret:String,
    @SerializedName("payment_session_token") var paymentSessionToken:String
    )