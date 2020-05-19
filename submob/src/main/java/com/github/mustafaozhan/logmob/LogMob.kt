/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import mustafaozhan.github.com.logmob.BuildConfig
import timber.log.Timber

fun initLogMob(
    context: Context,
    enableCrashlytics: Boolean = false,
    enableAnalytics: Boolean = false
) {

    if (!BuildConfig.DEBUG && enableAnalytics) {
        FirebaseAnalytics.getInstance(context)
    }

    FirebaseCrashlytics
        .getInstance()
        .setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)

    if (BuildConfig.DEBUG) {
        Timber.plant(CCCDebugTree(context))
    } else {
        if (enableCrashlytics) {
            Timber.plant(CrashlyticsTree())
        }
    }

    Thread.setDefaultUncaughtExceptionHandler(WatchDogHandler())
}
