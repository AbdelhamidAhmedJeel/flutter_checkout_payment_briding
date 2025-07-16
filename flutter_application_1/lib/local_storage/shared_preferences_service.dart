import 'package:flutter_application_1/local_storage/enums.dart';
import 'package:flutter_application_1/local_storage/shared_preferences_service_impl.dart';

abstract class SharedPreferencesService {
  static Future<SharedPreferencesService> getInstance() async {
    return await SharedPreferencesServiceImpl.getInstance();
  }

  Future saveData({
    required String key,
    required dynamic value,
    required DataType dataType,
  });

  Future<dynamic> getData({required String key, required DataType dataType});

  Future<bool> clearAll();
  Future<bool> clearKey({required String key});

  bool checkByKey({required String key});
}
