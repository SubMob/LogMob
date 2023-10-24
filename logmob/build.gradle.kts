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
    @Suppress("OPT_IN_USAGE")
    targetHierarchy.default()

    jvm()

    androidTarget {
        publishLibraryVariants("release", "debug")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.common.kermit)
            }
        }
        val androidMain by getting {
            dependencies {
                libs.android.apply {
                    implementation(firebaseCrashlytics)
                    implementation(anrWatchDog)
                }
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.common.kermitCrashlytics)
            }
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
