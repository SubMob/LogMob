/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    with(Dependencies.Plugins) {
        id(ANDROID_LIB)
        kotlin(MULTIPLATFORM)
        kotlin(COCOAPODS)
        `maven-publish`
        signing
    }
}
version = ProjectSettings.getVersionName(project)

kotlin {

    jvm()

    android {
        publishLibraryVariants("release", "debug")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    js()

    cocoapods {
        summary = "LogMob"
        homepage = "https://github.com/SubMob/LogMob"
        ios.deploymentTarget = "14.0"
        framework {
            baseName = "LogMob"
            export(Dependencies.Common.KERMIT)
        }
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {

        val commonMain by getting {
            dependencies {
                api(Dependencies.Common.KERMIT)
                implementation(Dependencies.Common.KERMIT_CRASHLYTICS)
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

@Suppress("UnstableApiUsage")
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
