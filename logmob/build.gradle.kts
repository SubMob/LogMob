/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    with(Plugins) {
        id(ANDROID_LIBRARY)
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

    // todo Revert to just ios() when gradle plugin can properly resolve it
    if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    js {
        browser {
            binaries.executable()
            testTask {
                enabled = false
            }
        }
    }

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
                    implementation(FIREBASE_CORE)
                    implementation(ANR_WATCH_DOG)
                }
            }
            val androidTest by getting
        }

        val iosMain by getting
        val iosTest by getting
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
    }
}

android {
    with(ProjectSettings) {
        compileSdk = COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
        }

        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }
}
