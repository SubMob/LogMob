/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.submob.logmob

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity
import co.touchlab.kermit.platformLogWriter
import com.google.firebase.crashlytics.FirebaseCrashlytics

actual abstract class BaseLogMobWriter : LogWriter() {
    private val logger = platformLogWriter()

    actual override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
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

            if (severity == Severity.Error) {
                FirebaseCrashlytics.getInstance().apply {
                    setCustomKey(CRASHLYTICS_KEY_PRIORITY, severity.name)
                    setCustomKey(CRASHLYTICS_KEY_TAG, tag)
                    setCustomKey(CRASHLYTICS_KEY_MESSAGE, message)
                    recordException(
                        throwable ?: Exception(message).also {
                            // To ensure that the Exceptions are not merged into single instance in long term
                            recordException(Exception("No throwable provided for error message: $message"))
                        }
                    )
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
