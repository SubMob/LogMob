/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.submob.logmob

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity

expect abstract class BaseLogMobWriter() : LogWriter {
    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?)
}
