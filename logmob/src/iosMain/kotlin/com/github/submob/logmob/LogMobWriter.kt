/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.submob.logmob

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity
import co.touchlab.kermit.platformLogWriter

actual class LogMobWriter : LogWriter() {

    private val logger = platformLogWriter()

    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        logger.log(
            severity = severity,
            tag = tag,
            throwable = throwable,
            message = message
        )
    }
}
