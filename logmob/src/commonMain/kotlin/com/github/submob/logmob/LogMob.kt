package com.github.submob.logmob

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger

@Suppress("unused")
fun initLogger(logWriters: List<LogWriter> = emptyList()) = Logger.setLogWriters(
    logWriters + LogMobWriter()
).also {
    Logger.v { "Logger initialized" }
}.let {
    Logger
}

fun initTestLogger() = Logger.setLogWriters(
    TestWriter()
).also {
    Logger.v { "Test Logger initialized" }
}
