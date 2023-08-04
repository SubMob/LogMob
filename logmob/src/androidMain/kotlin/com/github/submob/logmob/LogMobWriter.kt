/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.submob.logmob

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity
import co.touchlab.kermit.platformLogWriter
import com.google.firebase.crashlytics.FirebaseCrashlytics

actual class LogMobWriter : LogWriter() {
    private val logger = platformLogWriter()

    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        if (BuildConfig.DEBUG) {
            logger.log(
                severity = severity,
                tag = tag,
                throwable = throwable,
                message = "@${Thread.currentThread().name}: $message",
            )
        } else {
            FirebaseCrashlytics.getInstance().log(
                "${severity.name} @${Thread.currentThread().name} $message"
            )

            if (severity == Severity.Error || severity == Severity.Assert) {
                FirebaseCrashlytics.getInstance().apply {
                    setCustomKey(CRASHLYTICS_KEY_PRIORITY, severity.name)
                    setCustomKey(CRASHLYTICS_KEY_TAG, tag)
                    setCustomKey(CRASHLYTICS_KEY_MESSAGE, message)
                    recordException(throwable ?: Exception(message))
                }
            }
        }
    }

    companion object {
        private const val CRASHLYTICS_KEY_PRIORITY = "priority"
        private const val CRASHLYTICS_KEY_TAG = "tag"
        private const val CRASHLYTICS_KEY_MESSAGE = "message"
    }
}
