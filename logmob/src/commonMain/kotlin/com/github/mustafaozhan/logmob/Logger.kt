package com.github.mustafaozhan.logmob

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit

lateinit var kermit: Kermit

fun initLogger(forTest: Boolean = false) {
    kermit = Kermit(if (forTest) CommonLogger() else LogMobLogger())
}
