package com.github.mustafaozhan.logmob

import co.touchlab.kermit.Logger

fun initLogger(
    forTest: Boolean = false
) = Logger.setLogWriters(
    if (forTest) {
        TestWriter()
    } else {
        LogMobWriter()
    }
).let { Logger }
