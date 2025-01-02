plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.resepsehat"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.resepsehat"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("com.google.android.material:material:1.9.0") // Material Components
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0") // CoordinatorLayout

    // Tambahkan dependensi Gson
    implementation("com.google.code.gson:gson:2.8.9")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
