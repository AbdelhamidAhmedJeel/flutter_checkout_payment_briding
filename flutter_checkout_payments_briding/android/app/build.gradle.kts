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
    kapt {
        javacOptions {
            option("-source", "11")
            option("-target", "11")
        }
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

    implementation("com.checkout:checkout-sdk-3ds-android:3.2.6")
    implementation("com.checkout:checkout-android-components:1.0.1")
    // Required to initialize the CardManagementDesignSystem
    implementation("androidx.compose.ui:ui:1.8.3")
    implementation("com.checkout:checkout-sdk-card-management-android:2.0.0")

    // Corrected Retrofit dependencies
    implementation ("com.squareup.retrofit2:retrofit:3.0.0")
    implementation ("com.squareup.retrofit2:converter-gson:3.0.0")

    // Corrected AppCompat dependency
    implementation ("androidx.appcompat:appcompat:1.7.1")

    // ... your Dagger dependencies and other dependencies
    // Use your actual Dagger version
    implementation("com.google.dagger:dagger:2.57")

    kapt ("com.google.dagger:dagger-compiler:2.57") // Use your actual Dagger version
    // If using Dagger Android
    implementation ("com.google.dagger:dagger-android-support:2.57")
    kapt ("com.google.dagger:dagger-android-processor:2.57")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.10.2")



    implementation("androidx.multidex:multidex:2.0.1")

}
