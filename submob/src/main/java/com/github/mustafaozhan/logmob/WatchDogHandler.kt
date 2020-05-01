/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob

import com.github.anrwatchdog.ANRWatchDog
import timber.log.Timber

class WatchDogHandler : Thread.UncaughtExceptionHandler {
    private val chainedHandler: Thread.UncaughtExceptionHandler? =
        Thread.getDefaultUncaughtExceptionHandler()

    init {
        ANRWatchDog()
            .setReportMainThreadOnly()
            .setANRListener { error -> Timber.e(error, "ANR DETECTED") }.start()
    }

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        Timber.e(exception, "CRASH DETECTED on thread $thread")
        chainedHandler?.uncaughtException(thread, exception)
    }
}
