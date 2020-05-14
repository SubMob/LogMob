/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.firebase.analytics.FirebaseAnalytics
import io.fabric.sdk.android.Fabric
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

    Fabric.with(
        context,
        Crashlytics.Builder()
            .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
            .build()
    )

    if (BuildConfig.DEBUG) {
        Timber.plant(CCCDebugTree(context))
    } else {
        if (enableCrashlytics) {
            Timber.plant(CrashlyticsTree())
        }
    }

    Thread.setDefaultUncaughtExceptionHandler(WatchDogHandler())
}
