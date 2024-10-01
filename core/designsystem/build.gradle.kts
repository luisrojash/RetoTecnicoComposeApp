plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    // Descomenta si usas Dagger Hilt
    // kotlin("kapt")
    // id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.lerp.designsystem"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Ajusta esto según la versión del compilador que uses
    }
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material)
    implementation(libs.androidx.ui.tooling)
    implementation("androidx.compose.compiler:compiler:1.5.1") // Asegúrate de usar la versión compatible
    // Elimina esta línea si está duplicando la versión de Kotlin
    // implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0") // No es necesario si ya está configurado en el archivo de versiones
}
