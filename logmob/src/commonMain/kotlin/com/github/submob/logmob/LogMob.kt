package com.github.submob.logmob

import co.touchlab.kermit.Logger

@Suppress("unused")
fun initLogger() = Logger.setLogWriters(
    LogMobWriter()
).also {
    Logger.v { "Logger initialized" }
}

fun initTestLogger() = Logger.setLogWriters(
    TestWriter()
).also {
    Logger.v { "Test Logger initialized" }
}
