/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    libs.plugins.apply {
        alias(androidKotlinMultiplatformLibrary)
        alias(kotlinMultiplatform)
        `maven-publish`
        signing
    }
}

kotlin {
    androidLibrary {
        namespace = "com.github.submob.logmob"
        compileSdk = ProjectSettings.COMPILE_SDK_VERSION
    }

    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.common.kermit)
        }

        androidMain.dependencies {
            libs.android.apply {
                implementation(project.dependencies.platform(firebaseBom))
                implementation(firebaseCrashlytics)
                implementation(anrWatchDog)
            }
        }

        iosMain.dependencies {
            implementation(libs.common.kermitCrashlytics)
        }
    }
}
