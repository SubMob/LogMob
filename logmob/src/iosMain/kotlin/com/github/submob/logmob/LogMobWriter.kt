/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.submob.logmob

import co.touchlab.kermit.ExperimentalKermitApi
import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.crashlytics.CrashlyticsLogWriter
import co.touchlab.kermit.crashlytics.setupCrashlyticsExceptionHook
import co.touchlab.kermit.platformLogWriter

@OptIn(ExperimentalKermitApi::class)
actual class LogMobWriter : LogWriter() {

    init {
        Logger.addLogWriter(
            CrashlyticsLogWriter(
                minSeverity = Severity.Verbose,
                minCrashSeverity = Severity.Warn,
                printTag = true
            )
        )
        setupCrashlyticsExceptionHook(Logger)
    }

    private var logger = platformLogWriter()

    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        logger.log(
            severity = severity,
            tag = tag,
            throwable = throwable,
            message = message
        )
    }
}
