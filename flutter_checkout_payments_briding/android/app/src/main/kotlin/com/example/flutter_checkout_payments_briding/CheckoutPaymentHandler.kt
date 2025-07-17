package com.example.flutter_checkout_payments_briding
import androidx.compose.runtime.key
import com.google.gson.Gson
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class CheckoutPaymentHandler(
    private val activity: MainActivity
){

    private var publicKey:String? = null;
    private val baseUrl: String = "https://api.sandbox.checkout.com"
    private val  scope = CoroutineScope(Dispatchers.IO+ SupervisorJob())
    private  val  gson = Gson()

    fun handleMethodCall(call: MethodCall, result: MethodChannel.Result){
        when(call.method){
            "initialize" -> initialize(call,result)
        }
    }

  private  fun initialize(call: MethodCall,result: MethodChannel.Result){
        try{
            val incomingKey = call.argument<String>("publicKey")
            val isProduction = call.argument<Boolean>("isProduction")
            if(incomingKey ==null){
                result.error("Invalid Arguments","public key is required",null)
                return
            }
            publicKey = incomingKey
            result.success(true)

        }catch (e :Exception){
            result.error("Initialization error", e.message,null)
        }
    }
}