/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.submob.logmob

import com.google.firebase.crashlytics.FirebaseCrashlytics

fun initAndroidLogger(isCrashlyticsEnabled: Boolean = false) {

    if (isCrashlyticsEnabled) {
        FirebaseCrashlytics
            .getInstance()
            .setCrashlyticsCollectionEnabled(true)
    }

    initLogger()
}
