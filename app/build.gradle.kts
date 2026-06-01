import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

val secretsFile = rootProject.file("secrets.properties")
val secrets = Properties()
if (secretsFile.exists()) {
    secrets.load(secretsFile.inputStream())
}

android {
    namespace = "com.example.booksearch_asynctaskloader"
    compileSdk = 36

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.booksearch_asynctaskloader"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Lấy API Key từ secrets.properties, nếu không có sẽ để trống
        val apiKey = secrets.getProperty("GOOGLE_BOOKS_API_KEY") ?: ""
        buildConfigField("String", "GOOGLE_BOOKS_API_KEY", "\"$apiKey\"")
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
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.loader)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}