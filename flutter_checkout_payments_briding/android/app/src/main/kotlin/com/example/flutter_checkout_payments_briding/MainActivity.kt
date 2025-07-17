package com.example.flutter_checkout_payments_briding

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel




class MainActivity : FlutterActivity(){
    private val  channel = "com.example.flutter_checkout_payments_briding/checkout"
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            channel
        ).setMethodCallHandler { call, result ->
            val  checkoutPaymentHandler = CheckoutPaymentHandler(this)
            checkoutPaymentHandler.handleMethodCall(call,result)

    }
        }

}
