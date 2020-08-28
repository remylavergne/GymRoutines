plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Apps.compileSdk)
    buildToolsVersion("30.0.1")

    defaultConfig {
        applicationId = "com.noahjutz.gymroutines"
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.compileSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = Versions.kotlin
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

configurations.all {
    resolutionStrategy {
        force("org.antlr:antlr4-runtime:4.7.1")
        force("org.antlr:antlr4-tool:4.7.1")
    }
}

dependencies {
    implementation(Libs.core)

    implementation(Libs.coroutines)

    implementation(Libs.material)

    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)
    implementation(Libs.hiltExt)
    kapt(Libs.hiltExtCompiler)

    implementation(Libs.room)
    kapt(Libs.roomCompiler)
    implementation(Libs.roomRuntime)

    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.junitAndroid)
    androidTestImplementation(TestLibs.espresso)

    implementation(Libs.compose)
    implementation(Libs.composeFoundation)
    implementation(Libs.composeIcons)
    implementation(Libs.composeLivedata)
    implementation(Libs.composeMaterial)
    implementation(Libs.composeTooling)
    implementation(Libs.composeRuntime)
    implementation(Libs.composeRouter)

    implementation(Libs.lifecycle)
    implementation(Libs.livedata)

    // Ktlint
    // TODO
}
