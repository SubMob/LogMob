/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

@Suppress("SpellCheckingInspection")
object Dependencies {
    object Android {
        const val FIREBASE_CRASHLYTICS =
            "com.google.firebase:firebase-crashlytics:${Versions.FIREBASE_CRASHLYTICS}"
        const val FIREBASE_CORE = "com.google.firebase:firebase-core:${Versions.FIREBASE_CORE}"
        const val ANR_WATCH_DOG = "com.github.anrwatchdog:anrwatchdog:${Versions.ANR_WATCH_DOG}"
    }

    object Common {
        const val KERMIT = "co.touchlab:kermit:${Versions.KERMIT}"
    }
}
