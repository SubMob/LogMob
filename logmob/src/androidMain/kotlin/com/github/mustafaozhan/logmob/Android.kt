/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.mustafaozhan.logmob

import com.google.firebase.crashlytics.FirebaseCrashlytics
import mustafaozhan.github.com.logmob.BuildConfig

fun initCrashlytics() {
    FirebaseCrashlytics
        .getInstance()
        .setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
}
