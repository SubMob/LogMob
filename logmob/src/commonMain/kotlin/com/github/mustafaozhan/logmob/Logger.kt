package com.github.mustafaozhan.logmob

import co.touchlab.kermit.Logger

val kermit: Logger
    get() = Logger

fun initLogger(forTest: Boolean = false) {
    if (forTest) {
        // todo https://github.com/touchlab/Kermit/issues/184
        // Logger.setLogWriters(getTestLogWriter())
    } else {
        Logger.setLogWriters(listOf(LogMobWriter()))
    }
}
