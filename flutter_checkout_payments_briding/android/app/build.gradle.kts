plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")

    // hilt dependency injection
    id("kotlin-kapt")
}


android {
    namespace = "com.example.flutter_checkout_payments_briding"
    compileSdk = 36
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.example.flutter_checkout_payments_briding"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = 24
        targetSdk = 36
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    buildTypes {
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

flutter {
    source = "../.."
}

dependencies{

    // Required to initialize the CardManagementDesignSystem
    implementation("androidx.compose.ui:ui:1.8.3")
    implementation("com.checkout:checkout-sdk-card-management-android:2.0.0")

    // api dependencies
    implementation("com.squareup.okhttp3:okhttp:5.1.0")
    implementation("com.squareuo.retrofit2:retrofit:2.9.0")
    implementation("com.squareuo.retrofit2:converter-gson:2.9.0")

    // Android dependencies
    implementation("anroidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")

    // Json processing
    implementation("com.google.code.gson:gson:2.13.1")

    implementation("com.google.dagger:dagger:2.57")
    implementation("om.google.dagger:dagger-compiler:2.57")
    implementation("om.google.dagger:dagger-android-processor:2.57")







}
