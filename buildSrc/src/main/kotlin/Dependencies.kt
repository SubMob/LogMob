/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

@Suppress("SpellCheckingInspection")
object Dependencies {
    object Android {
        const val FIREBASE_CRASHLYTICS =
            "com.google.firebase:firebase-crashlytics-ktx:${Versions.FIREBASE_CRASHLYTICS}"
        const val ANR_WATCH_DOG = "com.github.anrwatchdog:anrwatchdog:${Versions.ANR_WATCH_DOG}"
    }

    object Common {
        const val KERMIT = "co.touchlab:kermit:${Versions.KERMIT}"
    }

    object ClassPaths {
        const val ANDROID_GRADLE_PLUGIN =
            "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    }

    object Plugins {
        const val ANDROID_LIB = "com.android.library"
        const val MULTIPLATFORM = "multiplatform"
    }
}
