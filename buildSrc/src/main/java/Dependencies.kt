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

    // Test
    const val junit = "4.12"
}

object Libs {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}

object Classpaths {
    const val gradle = "com.android.tools.build:gradle:4.2.0-alpha07"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}