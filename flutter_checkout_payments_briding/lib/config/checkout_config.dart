import 'package:flutter_dotenv/flutter_dotenv.dart';

class CheckoutConfig {
  static final String publicKey = dotenv.get('PUBLIC_KEY');
   static final String channelId = dotenv.get('CHEANNEL_ID');
}
