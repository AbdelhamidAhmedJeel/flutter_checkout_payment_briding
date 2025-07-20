import 'dart:convert';

import 'package:flutter/services.dart';
import 'package:flutter_checkout_payments_briding/models/params/payment_request_params.dart';
import 'package:flutter_checkout_payments_briding/models/payment_session_response.dart';

class CheckoutPaymentService {
  static const MethodChannel _methodChannel = MethodChannel(
    "com.example.flutter_checkout_payments_briding/checkout",
  );
  static Future<bool> initialize({
    required String publicKey,
    required bool isProduction,
  }) async {
    try {
      final result = await _methodChannel.invokeMethod('initialize', {
        'publicKey': publicKey,
        'isProduction': isProduction,
      });
      return result == true;
    } on PlatformException catch (e) {
      print('Failed to initilize Checkout ${e.message}');
      return false;
    }
  }

  static Future<PaymentSessionResponse?> createPaymentSession({
    required PaymentRequestParams params,
  }) async {
    try {
      final result = await _methodChannel.invokeMethod(
        'createPaymentSession',
        params.toMap(),
      );

      return PaymentSessionResponse.fromJson(jsonDecode(result));
    } on PlatformException catch (e) {
      print('${e.message}');
    }
  }
}
