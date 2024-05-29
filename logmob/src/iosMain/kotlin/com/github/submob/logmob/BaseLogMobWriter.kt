/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.submob.logmob

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity
import co.touchlab.kermit.platformLogWriter

actual abstract class BaseLogMobWriter : LogWriter() {

    private val logger = platformLogWriter()

    actual override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        logger.log(
            severity = severity,
            tag = tag,
            throwable = throwable,
            message = message
        )
    }
}
