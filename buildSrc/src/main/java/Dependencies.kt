object Apps {
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 5
    const val versionName = "0.1.0"
}

object Versions {
    const val gradle = "4.2.0-alpha07"
    const val kotlin = "1.4.0"
    const val hilt = "2.28-alpha"
    const val hiltExt = "1.0.0-alpha02"

    // Test
    const val junit = "4.12"
}

object Libs {
    const val core = "androidx.core:core-ktx:1.3.1"

    // Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltExt = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltExt}"
    const val hiltExtCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltExt}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}

object Classpaths {
    const val gradle = "com.android.tools.build:gradle:4.2.0-alpha07"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}