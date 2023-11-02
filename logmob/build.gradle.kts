/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    libs.plugins.apply {
        alias(androidLibrary)
        alias(kotlinMultiplatform)
        `maven-publish`
        signing
    }
}

kotlin {
    jvm()

    androidTarget {
        publishLibraryVariants("release", "debug")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.common.kermit)
        }

        androidMain.dependencies {
            libs.android.apply {
                implementation(firebaseCrashlytics)
                implementation(anrWatchDog)
            }
        }

        iosMain.dependencies {
            implementation(libs.common.kermitCrashlytics)
        }
    }
}

android {
    ProjectSettings.apply {
        namespace = "com.github.submob.logmob"
        compileSdk = COMPILE_SDK_VERSION

        compileOptions {
            sourceCompatibility = JAVA_VERSION
            targetCompatibility = JAVA_VERSION
        }

        buildFeatures.buildConfig = true
    }
}
