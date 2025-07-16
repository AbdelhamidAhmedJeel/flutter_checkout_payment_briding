import 'package:shared_preferences/shared_preferences.dart';
import 'package:flutter_application_1/local_storage/enums.dart';
import 'package:flutter_application_1/local_storage/shared_preferences_service.dart';

class SharedPreferencesServiceImpl implements SharedPreferencesService {
  static SharedPreferencesServiceImpl? _instance;
  static SharedPreferences? _prefs;

  SharedPreferencesServiceImpl._();

  static Future<SharedPreferencesServiceImpl> getInstance() async {
    if (_instance == null) {
      _instance = SharedPreferencesServiceImpl._();
      _prefs = await SharedPreferences.getInstance();
    }
    return _instance!;
  }

  SharedPreferences get prefs => _prefs!;

  @override
  Future<dynamic> getData({
    required String key,
    required DataType dataType,
  }) async {
    return await _getSharedPreferencesMethod(
      restoring: true,
      dataType: dataType,
    )(key);
  }

  @override
  Future saveData({
    required String key,
    required value,
    required DataType dataType,
  }) async {
    await _getSharedPreferencesMethod(restoring: false, dataType: dataType)(
      key,
      value,
    );
  }

  @override
  bool checkByKey({required String key}) {
    return prefs.containsKey(key);
  }

  @override
  Future<bool> clearAll() async {
    return await prefs.clear();
  }

  @override
  Future<bool> clearKey({required String key}) {
    return prefs.remove(key);
  }

  _getSharedPreferencesMethod({
    required bool restoring,
    required DataType dataType,
  }) {
    switch (dataType) {
      case DataType.string:
        return restoring ? prefs.getString : prefs.setString;
      case DataType.bool:
        return restoring ? prefs.getBool : prefs.setBool;
      case DataType.int:
        return restoring ? prefs.getInt : prefs.setInt;
      case DataType.double:
        return restoring ? prefs.getDouble : prefs.setDouble;
      case DataType.stringList:
        return restoring ? prefs.getStringList : prefs.setStringList;
    }
  }
}
