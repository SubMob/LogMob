/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob.handler

import com.github.anrwatchdog.ANRWatchDog
import com.github.mustafaozhan.logmob.kermit

class WatchDogHandler : Thread.UncaughtExceptionHandler {

    private val chainedHandler = Thread.getDefaultUncaughtExceptionHandler()

    init {
        ANRWatchDog(TIME_OUT)
            .setReportMainThreadOnly()
            .setANRListener { error ->
                kermit.e(error) { "ANR DETECTED" }
            }.start()
    }

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        kermit.e(exception) { "CRASH DETECTED on thread $thread" }
        chainedHandler?.uncaughtException(thread, exception)
    }

    companion object {
        private const val TIME_OUT = 10000
    }
}
