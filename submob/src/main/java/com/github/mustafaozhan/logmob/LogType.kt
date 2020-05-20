/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob

import android.util.Log

enum class LogType(
    val typeID: Int,
    val typeName: String
) {
    VERBOSE(Log.VERBOSE, "VERBOSE"),
    DEBUG(Log.DEBUG, "DEBUG"),
    INFO(Log.INFO, "INFO"),
    WARN(Log.WARN, "WARN"),
    ERROR(Log.ERROR, "ERROR"),
    ASSERT(Log.ASSERT, "ASSERT")
}
