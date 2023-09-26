package com.github.submob.logmob

import co.touchlab.crashkios.crashlytics.setCrashlyticsUnhandledExceptionHook
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.crashlytics.CrashlyticsLogWriter

@Suppress("OPT_IN_USAGE", "unused")
fun initCrashlytics() {
    Logger.addLogWriter(
        CrashlyticsLogWriter(
            minSeverity = Severity.Verbose,
            minCrashSeverity = Severity.Warn
        )
    )
    setCrashlyticsUnhandledExceptionHook()
}
