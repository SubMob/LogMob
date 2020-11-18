/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.mustafaozhan.logmob

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity

@Suppress("unused")
actual class LogMobLogger : Logger() {
    private val logger = CommonLogger()
    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        logger.log(severity, message, tag, throwable)
    }
}
