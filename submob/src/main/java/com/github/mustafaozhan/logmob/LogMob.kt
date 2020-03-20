@file:Suppress("unused", "TooManyFunctions")

package com.github.mustafaozhan.logmob

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric
import mustafaozhan.github.com.logmob.BuildConfig
import timber.log.Timber

fun initLogMob(context: Context, enableCrashlytics: Boolean = false) {
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

fun logVerbose(e: Throwable) = Timber.v(e)
fun logVerbose(e: Throwable, message: String) = Timber.v(e, message)

fun logDebug(e: Throwable) = Timber.d(e)
fun logDebug(e: Throwable, message: String) = Timber.d(e, message)

fun logInfo(e: Throwable) = Timber.i(e)
fun logInfo(e: Throwable, message: String) = Timber.i(e, message)

fun logWarning(e: Throwable) = Timber.w(e)
fun logWarning(e: Throwable, message: String) = Timber.w(e, message)

fun logError(e: Throwable) = Timber.e(e)
fun logError(e: Throwable, message: String) = Timber.e(e, message)

fun logAssert(e: Throwable) = Timber.wtf(e)
fun logAssert(e: Throwable, message: String) = Timber.wtf(e, message)
