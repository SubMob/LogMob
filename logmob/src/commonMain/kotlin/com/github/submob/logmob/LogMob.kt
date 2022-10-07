package com.github.submob.logmob

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger

@Suppress("unused")
fun initLogger(
    writers: List<LogWriter> = listOf(LogMobWriter())
) = Logger.setLogWriters(writers).also {
    Logger.v { "Logger initialized" }
}

fun initTestLogger() = Logger.setLogWriters(TestWriter()).also {
    Logger.v { "Test Logger initialized" }
}
