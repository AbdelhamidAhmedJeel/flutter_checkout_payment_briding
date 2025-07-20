import 'package:flutter/material.dart';
import 'package:flutter_checkout_payments_briding/config/checkout_config.dart';
import 'package:flutter_checkout_payments_briding/models/params/payment_request_params.dart';
import 'package:flutter_checkout_payments_briding/models/payment_session_response.dart';
import 'package:flutter_checkout_payments_briding/services/checkout_payment_service.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';

void main() async {
  await dotenv.load(fileName: ".env");
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: MainScreen());
  }
}

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  bool isInitializing = true;
  bool isInitialized = false;
  String statusMessage = '';
  String? _paymentSessionId;
  @override
  void initState() {
    super.initState();
    _initialize();
  }

  void _initialize() async {
    setState(() {
      isInitializing = true;
      statusMessage = "Initializing Checkout";
    });
    try {
      final success = await CheckoutPaymentService.initialize(
        publicKey: CheckoutConfig.publicKey,
        isProduction: false,
      );
      setState(() {
        isInitialized = success;
        isInitializing = false;
        statusMessage = success
            ? 'Checkout initialized successfully'
            : 'Failed to initialize Checkout';
      });
    } catch (e) {
      setState(() {
        statusMessage = 'Error Initializing $e';
        isInitializing = false;
      });
    }
  }

  Future _createPaymentSession() async {
    setState(() {
      isInitializing = true;
      statusMessage = 'Creating payment session';
    });
    try {
      PaymentRequestParams params = PaymentRequestParams(
        channelId: CheckoutConfig.channelId,
        amount: 100,
        currency: 'SAR',
        country: 'SA',
        name: 'Abdelhamid',
        email: 'abdelhamid.ahmed.abdo@gmail.com',
      );
      PaymentSessionResponse? response =
          await CheckoutPaymentService.createPaymentSession(params: params);

      setState(() {
        isInitializing = false;
        _paymentSessionId = response?.id;
        statusMessage = 'Payment Session Created Successfully';
      });
    } catch (e) {
      print('Failed to create payment session $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(statusMessage),
            SizedBox(height: 10),
            if (isInitializing) CircularProgressIndicator(),
            SizedBox(height: 10),
            if (isInitialized)
              Column(
                children: [
                  Text('Checkout Order for 100.0 SAR'),
                  ElevatedButton(
                    onPressed: () {
                      _createPaymentSession();
                    },
                    child: Text('Create payment Session'),
                  ),
                ],
              ),

            if (_paymentSessionId != null)
              Text('Session ID :${_paymentSessionId ?? ''}'),
            if (_paymentSessionId != null)
              ElevatedButton(
                onPressed: () {},
                child: Text('Render Payment UI'),
              ),
          ],
        ),
      ),
    );
  }
}
