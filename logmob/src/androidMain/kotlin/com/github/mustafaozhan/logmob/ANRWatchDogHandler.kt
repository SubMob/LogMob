/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob

import co.touchlab.kermit.Logger
import com.github.anrwatchdog.ANRWatchDog

class ANRWatchDogHandler : Thread.UncaughtExceptionHandler {

    private val chainedHandler = Thread.getDefaultUncaughtExceptionHandler()

    init {
        ANRWatchDog(TIME_OUT)
            .setReportMainThreadOnly()
            .setANRListener { error ->
                Logger.e(error) { "ANR DETECTED" }
            }.start()
    }

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        Logger.e(exception) { "CRASH DETECTED on thread $thread" }
        chainedHandler?.uncaughtException(thread, exception)
    }

    companion object {
        private const val TIME_OUT = 10000
    }
}
