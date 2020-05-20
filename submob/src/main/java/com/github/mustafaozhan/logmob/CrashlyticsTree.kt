/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {
    companion object {
        private const val CRASHLYTICS_KEY_PRIORITY = "priority"
        private const val CRASHLYTICS_KEY_TAG = "tag"
        private const val CRASHLYTICS_KEY_MESSAGE = "message"
    }

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }

        FirebaseCrashlytics.getInstance().apply {
            LogType.values()
                .firstOrNull { it.typeID == priority }
                ?.typeName?.let {
                    setCustomKey(CRASHLYTICS_KEY_PRIORITY, it)
                }
            tag?.let { setCustomKey(CRASHLYTICS_KEY_TAG, it) }
            setCustomKey(CRASHLYTICS_KEY_MESSAGE, message)
            recordException(throwable ?: NonThrowableException(message))
        }
    }
}
