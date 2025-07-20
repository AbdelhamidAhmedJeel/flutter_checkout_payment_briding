package com.example.flutter_checkout_payments_briding.network.responses

import com.google.gson.annotations.SerializedName

data  class JeelServerResponse(@SerializedName ("content") var responseData:Any? =null
,@SerializedName("errors") var errors: List<JeelServerErrors>? =null)

data class JeelServerErrors(@SerializedName("id") val id: String? =null,@SerializedName("messgae") val message: String? = null,
    @SerializedName("description") val description:String? = null)