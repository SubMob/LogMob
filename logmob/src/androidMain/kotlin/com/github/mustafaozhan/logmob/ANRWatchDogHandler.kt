/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob

import co.touchlab.kermit.Logger
import com.github.anrwatchdog.ANRWatchDog

class ANRWatchDogHandler(timeOut: Int) : Thread.UncaughtExceptionHandler {

    private val chainedHandler = Thread.getDefaultUncaughtExceptionHandler()

    init {
        ANRWatchDog(timeOut)
            .setReportMainThreadOnly()
            .setANRListener { error ->
                Logger.e(Exception(error.message.toString())) { "ANR DETECTED" }
            }.start()
    }

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        Logger.e(exception) { "CRASH DETECTED on thread $thread" }
        chainedHandler?.uncaughtException(thread, exception)
    }
}
