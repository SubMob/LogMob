/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    libs.plugins.apply {
        alias(androidKotlinMultiplatformLibrary)
        alias(kotlinMultiplatform)
        alias(mavenPublish)
    }
}

kotlin {
    android {
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

mavenPublishing {
    // Coordinates (GROUP + POM_ARTIFACT_ID), POM, host and auto-release come from gradle.properties.
    // Sign releases only — Central Portal snapshots aren't signed, and the signing key is provided
    // to release CI via ORG_GRADLE_PROJECT_* env.
    if (!version.toString().endsWith("SNAPSHOT")) {
        signAllPublications()
    }
}
