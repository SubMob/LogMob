/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.mustafaozhan.logmob

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.platformLogWriter

actual class LogMobWriter : LogWriter() {
    private val logger = platformLogWriter()

    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        Logger.log(
            severity = severity,
            tag = tag,
            throwable = throwable,
            message = "@${Thread.currentThread().name}: $message",
        )
    }
}
