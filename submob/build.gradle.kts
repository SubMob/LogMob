/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
plugins {
    with(Plugins) {
        id(library)
        kotlin(android)
        kotlin(kapt)
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
    }
}

dependencies {
    with(Dependencies) {
        implementation(kotlin)
        implementation(firebaseCrashlytics)
        implementation(firebaseCore)
        implementation(timber)
        implementation(anrWatchDog)
    }
}
