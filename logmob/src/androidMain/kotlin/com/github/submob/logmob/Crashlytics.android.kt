/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.submob.logmob

import com.google.firebase.crashlytics.FirebaseCrashlytics

fun setCrashlyticsCollection(shouldEnable: Boolean) {
    FirebaseCrashlytics
        .getInstance()
        .setCrashlyticsCollectionEnabled(shouldEnable)
}
