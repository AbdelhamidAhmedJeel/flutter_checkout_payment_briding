class PaymentRequestParams {
  final String channelId;
  final int amount;
  final String currency;
  final String country;
  final String name;
  final String email;

  PaymentRequestParams( {
    required this.channelId,
    required this.amount,
    required this.currency,
    required this.country,
    required this.name,
    required this.email,
  });

  Map<String, dynamic> toMap() {
    return {
      'channelId':channelId,
      'amount': amount,
      'currency': currency,
      'country': country,
      'name': name,
      'email': email,
    };
  }
}
