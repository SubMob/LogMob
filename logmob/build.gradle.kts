/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    with(Plugins) {
        id(androidLibrary)
        kotlin(multiplatform)
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
                implementation(Dependencies.Common.kermit)
            }
        }
        val commonTest by getting

        with(Dependencies.Android) {
            val androidMain by getting {
                dependencies {
                    implementation(firebaseCrashlytics)
                    implementation(firebaseCore)
                    implementation(anrWatchDog)
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
        compileSdkVersion(projectCompileSdkVersion)

        defaultConfig {
            minSdkVersion(projectMinSdkVersion)
            targetSdkVersion(projectTargetSdkVersion)

            versionCode = getVersionCode(project)
            versionName = getVersionName(project)
        }

        // todo https://youtrack.jetbrains.com/issue/KT-43944
        configurations {
            create("testApi") {}
            create("testDebugApi") {}
            create("testReleaseApi") {}
        }

        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }
}
