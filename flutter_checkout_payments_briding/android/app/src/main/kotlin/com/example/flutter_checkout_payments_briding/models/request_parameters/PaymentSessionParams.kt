package com.example.flutter_checkout_payments_briding.models.request_parameters

import com.google.gson.annotations.SerializedName


data class PaymentSessionParams(
    @SerializedName("processing_channel_id") var processingChannelId: String ,
    @SerializedName("amount")  val amount: Int,
    @SerializedName("currency") val currency: String,
    @SerializedName("reference") val reference: String,
    @SerializedName("billing") val billing: Billing,
    @SerializedName("customer")  val customer: Customer,
    @SerializedName("success_url") val successUrl: String = "https://example.com/success",
    @SerializedName("failure_url") val failureUrl: String = "https://example.com/failure",
)

data class Billing(
    @SerializedName("address")  val address: Address
)

data class Address(
    @SerializedName("country") val country: String
)

data class Customer(
    @SerializedName("name") val name: String,
    @SerializedName("email")  val email: String
)
