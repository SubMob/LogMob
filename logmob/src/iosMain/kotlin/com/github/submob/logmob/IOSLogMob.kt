package com.github.submob.logmob

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.crashlytics.CrashlyticsLogWriter
import co.touchlab.kermit.crashlytics.setupCrashlyticsExceptionHook

@Suppress("OPT_IN_USAGE", "unused")
fun initIOSLogger(
    isCrashlyticsEnabled: Boolean = false
) = if (isCrashlyticsEnabled) {
    initLogger(
        listOf(
            LogMobWriter(),
            CrashlyticsLogWriter(
                minSeverity = Severity.Verbose,
                minCrashSeverity = Severity.Warn,
                printTag = true
            )
        )
    )
    setupCrashlyticsExceptionHook(Logger)
} else {
    initLogger()
}
