package com.github.submob.logmob

import co.touchlab.kermit.Logger

fun initLogger(
    forTest: Boolean = false
) = Logger.setLogWriters(
    if (forTest) {
        TestWriter()
    } else {
        LogMobWriter()
    }
).also { Logger.v { "Logger initialized" } }
