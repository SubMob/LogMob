/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.logmob

import co.touchlab.kermit.Logger
import com.github.anrwatchdog.ANRWatchDog

class ANRWatchDogHandler : Thread.UncaughtExceptionHandler {
    init {
        ANRWatchDog()
            .setReportMainThreadOnly()
            .setANRListener { error ->
                Logger.e(Exception(error.message.toString())) { "ANR DETECTED" }
            }.start()
    }

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        Logger.e(exception) { "CRASH DETECTED on thread $thread" }
        Thread.getDefaultUncaughtExceptionHandler()?.uncaughtException(thread, exception)
    }
}
