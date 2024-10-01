plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.lerp.infrastructure"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Esta debe ser la versión del compilador que usas
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":home:domain"))
    implementation(project(":core:domain"))
    implementation(project(":core:networking"))
    implementation(libs.google.dagger.hilt)
    implementation(libs.androidx.navigation.hilt)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.squareupRetrofit)
    implementation(libs.squareupRetrofitGson)
    kapt(libs.google.dagger.hilt.compiler)
}