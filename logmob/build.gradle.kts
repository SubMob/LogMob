/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    with(Dependencies.Plugins) {
        id(ANDROID_LIB)
        kotlin(MULTIPLATFORM)
        `maven-publish`
        signing
    }
}

kotlin {

    jvm()

    android {
        publishLibraryVariants("release", "debug")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    js()

    @Suppress("UNUSED_VARIABLE")
    sourceSets {

        val commonMain by getting {
            dependencies {
                api(Dependencies.Common.KERMIT)
            }
        }
        val commonTest by getting

        with(Dependencies.Android) {
            val androidMain by getting {
                dependencies {
                    implementation(FIREBASE_CRASHLYTICS)
                    implementation(ANR_WATCH_DOG)
                }
            }
            val androidTest by getting
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(Dependencies.Common.KERMIT_CRASHLYTICS)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
    }
}

android {
    with(ProjectSettings) {
        namespace = "com.github.submob.logmob"
        compileSdk = COMPILE_SDK_VERSION

        @Suppress("UnstableApiUsage")
        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
        }
    }
}
