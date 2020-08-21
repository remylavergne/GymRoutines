// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version = "1.4.0" // TODO: Use Dependencies.kt reference
    val hilt_version = "2.28-alpha" // TODO: Use Dependencies.kt reference
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Classpaths.gradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version") // TODO: Use Dependencies.kt reference
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0") // TODO: Use Dependencies.kt reference
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version") // TODO: Use Dependencies.kt reference
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean",Delete::class) {
    delete(rootProject.buildDir)
}