class PaymentSessionResponse {
  final String id;
  final String payementSessionSecret;
  final String paymentSessionToken;

  PaymentSessionResponse({
    required this.id,
    required this.payementSessionSecret,
    required this.paymentSessionToken,
  });

  factory PaymentSessionResponse.fromJson(Map<String, dynamic> json) {
    return PaymentSessionResponse(
      id: json['id'],
      payementSessionSecret: json['payment_session_secret'],
      paymentSessionToken: json['payment_session_token'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'payment_session_secret': payementSessionSecret,
      'payment_session_token': paymentSessionToken,
    };
  }
}
