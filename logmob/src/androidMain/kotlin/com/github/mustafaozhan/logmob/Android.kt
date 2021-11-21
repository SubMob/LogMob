/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.mustafaozhan.logmob

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import mustafaozhan.github.com.logmob.BuildConfig

fun initCrashlytics(
    context: Context,
    enableAnalytics: Boolean = false,
    enableAnrWatchDog: Boolean = false
) {

    if (!BuildConfig.DEBUG && enableAnalytics) {
        FirebaseAnalytics.getInstance(context)
    }

    FirebaseCrashlytics
        .getInstance()
        .setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)

    if (enableAnrWatchDog) {
        Thread.setDefaultUncaughtExceptionHandler(ANRWatchDogHandler())
    }
}
