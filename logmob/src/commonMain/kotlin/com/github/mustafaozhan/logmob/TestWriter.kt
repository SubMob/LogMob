package com.github.mustafaozhan.logmob

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity

class TestWriter : LogWriter() {
    override fun log(
        severity: Severity,
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        println("${severity.name}: $tag $message ${throwable ?: ""}")
    }
}
