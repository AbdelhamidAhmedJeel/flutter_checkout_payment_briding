import 'package:flutter/material.dart';
import 'package:flutter_application_1/local_storage/enums.dart';
import 'package:flutter_application_1/local_storage/constants.dart';
import 'package:flutter_application_1/local_storage/shared_preferences_service.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  late SharedPreferencesService mySharedPreferences;
  bool isLoading = true;
  String stringTest = "Jeel Pay";
  int intTest = 2025;
  bool boolTest = true;
  double doubleTest = 3.14;
  List<String> stringListTest = ["Abdelhamid", "Ahmed"];
  dynamic textToDisplay = 'Try Save Data With Shared Preferences';

  @override
  void initState() {
    super.initState();
    _initializeSharedPreferences();
  }

  Future _initializeSharedPreferences() async {
    mySharedPreferences = await SharedPreferencesService.getInstance();
    setState(() {
      isLoading = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,

        title: Text(widget.title),
      ),
      body: isLoading
          ? Center(child: CircularProgressIndicator())
          : Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Text('$textToDisplay'),
                  SizedBox(height: 30),
                  ElevatedButton(
                    onPressed: () async {
                      await _saveAndDisplayData(
                        key: Constants.stringKey,
                        value: stringTest,
                        dataType: DataType.string,
                      );
                    },
                    child: Text('Save String'),
                  ),
                  SizedBox(height: 30),
                  ElevatedButton(
                    onPressed: () async {
                      await _saveAndDisplayData(
                        key: Constants.intKey,
                        value: intTest,
                        dataType: DataType.int,
                      );
                    },
                    child: Text('Save integer'),
                  ),
                  SizedBox(height: 30),
                  ElevatedButton(
                    onPressed: () async {
                      await _saveAndDisplayData(
                        key: Constants.doubleKey,
                        value: doubleTest,
                        dataType: DataType.double,
                      );
                    },
                    child: Text('Save double'),
                  ),
                  SizedBox(height: 30),
                  ElevatedButton(
                    onPressed: () async {
                      await _saveAndDisplayData(
                        key: Constants.boolKey,
                        value: boolTest,
                        dataType: DataType.bool,
                      );
                    },
                    child: Text('Save boolean'),
                  ),
                  SizedBox(height: 30),
                  ElevatedButton(
                    onPressed: () async {
                      await _saveAndDisplayData(
                        key: Constants.stringKey,
                        value: stringListTest,
                        dataType: DataType.stringList,
                      );
                    },
                    child: Text('Save string list'),
                  ),
                ],
              ),
            ),
    );
  }

  Future _saveAndDisplayData({
    required String key,
    required dynamic value,
    required DataType dataType,
  }) async {
    isLoading = true;
    setState(() {});
    await mySharedPreferences
        .saveData(key: key, value: value, dataType: dataType)
        .then((onValue) async {
          dynamic results = await mySharedPreferences.getData(
            key: key,
            dataType: dataType,
          );
          debugPrint('$results');
          textToDisplay = '$results';
          isLoading = false;
          setState(() {});
        });
  }
}
