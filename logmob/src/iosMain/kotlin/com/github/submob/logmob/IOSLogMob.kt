package com.github.submob.logmob

import co.touchlab.kermit.Severity
import co.touchlab.kermit.crashlytics.CrashlyticsLogWriter
import co.touchlab.kermit.crashlytics.setupCrashlyticsExceptionHook

@Suppress("unused", "OPT_IN_USAGE")
fun initIOSLogger(isCrashlyticsEnabled: Boolean = false) {
    if (isCrashlyticsEnabled) {
        initLogger(
            listOf(
                CrashlyticsLogWriter(
                    minSeverity = Severity.Verbose,
                    minCrashSeverity = Severity.Warn,
                    printTag = true
                )
            )
        ).also {
            setupCrashlyticsExceptionHook(it)
        }
    } else {
        initLogger()
    }
}
